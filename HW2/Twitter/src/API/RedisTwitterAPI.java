//package API;
//
//import java.util.*;
//import redis.clients.jedis.*;
//
//public class RedisTwitterAPI implements TwitterAPI {
//
//
//    Jedis jedis = new Jedis("localhost");
//
//
//    public void reset() {
//        jedis.flushAll();
//    }
//
//
//    public void postTweet(Tweet t, boolean broadcast)
//    {
//        String key = "tweet:"+t.getUserID()+":"+getNextID();
//        String value = t.toString();
//        jedis.set(key,value);
//
//        if (broadcast)
//        {
//            Set<String> followers = jedis.smembers("followers:"+t.getUserID());
//            for (String f : followers)
//               addToTimeline(t, f);
//
//        }
//    }
//
//
//
//
//    public void addFollower(String userID, String followerID)
//    {
//        String key = "followers:"+userID;
//        jedis.sadd(key, followerID);
//    }
//
//    public List<Tweet> getTimeline(String userID) {
//        return null;
//    }
//
//    public List<String> getFollowers(String userID) {  return null;  }
//
//
//}
