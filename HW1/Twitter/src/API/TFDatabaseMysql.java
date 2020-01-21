package API;

import java.text.SimpleDateFormat;

public class TFDatabaseMysql implements TFDatabaseAPI{

    DBUtils dbu;

    public int registerFollower(Follower f){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "INSERT INTO followers (id,user_id,follows_id) VALUES" +
                    "('"+f.getId()+"','"+f.getUserId()+"','"+f.getFollowsId()+"')";
        return dbu.insertOneRecord(sql);
    }


    public int insertTweet(Tweet t){
        // build and run query
        String sql = "INSERT INTO tweets (id,tweetId,userId,tweetTS, tweetText) VALUES" +
                "('"+t.getId()+"','"+t.getTweetId()+"',"+t.getUserId()+"',"+t.getTweetTS()+"',"+t.getTweetText()+")";

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
