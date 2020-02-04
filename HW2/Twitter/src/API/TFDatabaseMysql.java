package API;

import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static API.Tweet.NUM_FIELDS;

public class TFDatabaseMysql implements TFDatabaseAPI {

    DBUtils dbu = new DBUtils("jdbc:mysql://localhost:3306/ds4300-assignment1?serverTimezone=EST5EDT", "root", "Nice3rock!");

    public int registerFollower(Follower f){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "INSERT INTO followers (user_id,follows_id) VALUES" +
                    "('"+f.getUserId()+"','"+f.getFollowsId()+"')";
        return dbu.insertOneRecord(sql);
    }


    public int insertTweet(Tweet t, boolean... broadcast){
        // build and run query

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String ts = sdf.format(t.getTweetTS());

        String sql = "INSERT INTO tweets (tweet_id,user_id,tweet_ts, tweet_text) VALUES" +
                "('" + t.getTweetId()+"','"+t.getUserId()+"','"+ts+"','"+t.getTweetText()+"')";

        // Return new doctor ID

        return dbu.insertOneRecord(sql);
    }

    public List<String[]> getTimeline(int user_id) throws Exception {
        // Build List of String arrays to represent results
        ArrayList<String[]> rows = new ArrayList<>();

        // Grab given user's timeline
        System.out.println("Grabbing user " + user_id);
        ResultSet rs = dbu.timeline(user_id);

        // For each tweet in the result set, parse it into a string array.
        // Needed to conform to return type of redis.
        while (rs.next()) {
            String[] row = new String[NUM_FIELDS];
            for(int i = 1; i <= NUM_FIELDS; i++){
                row[i-1] = rs.getString(i);
            }
            rows.add(row);
        }

        return rows;
    }


    public void authenticate(String user, String password){
        dbu = new DBUtils("", user, password);
    }

    /**
     * Close the connection when application finishes
     */
    public void closeConnection(){
        dbu.closeConnection();
    }

    @Override
    public void printTweets(List<String[]> tweets) {
        for (String[] tweet : tweets) {
            System.out.println(Arrays.toString(tweet));
        }
    }
}
