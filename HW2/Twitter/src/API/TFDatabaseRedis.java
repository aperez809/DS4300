package API;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class TFDatabaseRedis implements TFDatabaseAPI {

  Jedis jedis = new Jedis("localhost");

  @Override
  public int registerFollower(Follower f) {
    String key = "followers:"+ f.getUserId();
    Long out = jedis.sadd(key, String.valueOf(f.getFollowsId()));
    return Math.toIntExact(out);
  }

  @Override
  public int insertTweet(Tweet t, boolean... broadcast) {

    String key = "tweet:"+t.getUserId()+":"+getNextID();
    String value = t.toString();
    jedis.set(key,value);

    if (broadcast.length >= 1) {
      Set<String> followers = jedis.smembers("followers:" + t.getUserId());
      for (String f : followers)
        addToTimeline(t, f);
    }

    return 1;
  }

  @Override
  public void authenticate(String user, String password) {
    // Empty for this impl
    return;
  }

  @Override
  public void closeConnection() {
    jedis.disconnect();
  }

  @Override
  public String getTimeline(int user_id) throws Exception {
    String key = "timeline:" + Integer.toString(user_id);
    String val = jedis.get(key);
    return val;
  }

  private long getNextID()
  {
    long next = jedis.incr("nextTweetID");
    return next;
  }


  private void addToTimeline(Tweet t, String userID)
  {
    String key = "timeline:"+userID;
    String value = t.toString();
    jedis.lpush(key, value);
  }


}
