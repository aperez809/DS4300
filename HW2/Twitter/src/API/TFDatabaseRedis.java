package API;

import java.sql.ResultSet;

import redis.clients.jedis.Jedis;

public class TFDatabaseRedis implements TFDatabaseAPI {

  Jedis jedis = new Jedis("localhost");

  @Override
  public int registerFollower(Follower f) {
    {
      String key = "followers:"+ f.getUserId();
      jedis.sadd(key, String.valueOf(f.getFollowsId()));
    }
    return
  }

  @Override
  public int insertTweet(Tweet t) {
    return 0;
  }

  @Override
  public void authenticate(String user, String password) {

  }

  @Override
  public void closeConnection() {
  }

  @Override
  public ResultSet getTimeline(int user_id) throws Exception {
    return null;
  }
}
