package API;

import java.sql.ResultSet;

public interface TFDatabaseAPI {
    public int registerFollower(Follower f);

    public int insertTweet(Tweet t);

    public void authenticate(String user, String password);

    public void closeConnection();

    public ResultSet getTimeline(int user_id) throws Exception;

}
