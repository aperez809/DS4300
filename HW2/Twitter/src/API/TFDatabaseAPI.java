package API;


public interface TFDatabaseAPI {

    /***
     * Registers the follower object with the database
     * @param f
     * @return
     */
    public int registerFollower(Follower f);

    public int insertTweet(Tweet t, boolean... b);

    public void authenticate(String user, String password);

    public void closeConnection();

    public String getTimeline(int user_id) throws Exception;

}
