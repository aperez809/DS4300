package API;


public interface TFDatabaseAPI {

    /***
     * Registers the follower object with the database
     * @param f
     * @return
     */
    int registerFollower(Follower f);

    /***
     * Inserts the given tweet into the database.  If given a braodcast value, will also store that
     * tweet on all the timelines of those who follow that tweet.
     * @param t
     * @param broadcast
     * @return
     */
    int insertTweet(Tweet t, boolean... broadcast);

    /***
     * Authenticates the user to the underlying DB
     * @param user
     * @param password
     */
    void authenticate(String user, String password);

    /***
     * Closes the connection to the underlying DB
     */
    void closeConnection();

    /***
     * Returns the timeline of the given user_id
     * @param user_id
     * @return
     * @throws Exception
     */
    String getTimeline(int user_id) throws Exception;

}
