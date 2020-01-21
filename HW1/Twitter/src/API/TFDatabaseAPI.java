package API;

public interface TFDatabaseAPI {
    public int registerFollower(Follower f);

    public int insertTweet(Tweet t);

    public void authenticate(String user, String password);

    public void closeConnection();

}
