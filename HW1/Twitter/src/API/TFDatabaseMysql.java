package API;

import java.text.SimpleDateFormat;

public class TFDatabaseMysql implements TFDatabaseAPI{

    DBUtils dbu = new DBUtils("jdbc:mysql://localhost:3306/ds4300-assignment1?serverTimezone=EST5EDT", "root", "Nice3rock!");

    public int registerFollower(Follower f){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "INSERT INTO followers (user_id,follows_id) VALUES" +
                    "('"+f.getUserId()+"','"+f.getFollowsId()+"')";
        return dbu.insertOneRecord(sql);
    }


    public int insertTweet(Tweet t){
        // build and run query

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String ts = sdf.format(t.getTweetTS());

        String sql = "INSERT INTO tweets (tweet_id,user_id,tweet_ts, tweet_text) VALUES" +
                "('" + t.getTweetId()+"','"+t.getUserId()+"','"+ts+"','"+t.getTweetText()+"')";

        // Return new doctor ID

        return dbu.insertOneRecord(sql);
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
}
