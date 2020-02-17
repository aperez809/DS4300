package API;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Twitter {
  //    private static TFDatabaseAPI mySqlApi = new TFDatabaseMysql();
  private static TFDatabaseAPI redisApi = new TFDatabaseRedis();

  public static void main(String[] args) throws Exception {
    BufferedReader csvReaderTweets = new BufferedReader(new FileReader("/Users/owen/opt/ds4300/DS4300/HW1/tweets.csv"));
    BufferedReader csvReaderFollowers = new BufferedReader(new FileReader("/Users/owen/opt/ds4300/DS4300/HW1/user_followers.csv"));

    String row;


//    // Follower table load
//    while ((row = csvReaderFollowers.readLine()) != null) {
//      String[] data = row.split(",");
//      Follower f = new Follower(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
//      redisApi.registerFollower(f);
//    }
//    csvReaderFollowers.close();


    // To test how long it takes to load tweets
    long loadStartTime = System.nanoTime();
    System.out.println("Starting Tweets Load");

    // Tweet table load
    while (true) {
      row = csvReaderTweets.readLine();
      if (row == null) {
        break;
      }

      String[] data = row.split(",");
      Date d;
      try {
        d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse(data[2]);
      } catch (java.text.ParseException e) {
        d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data[2]);
      }
      Tweet recent = new Tweet(Integer.parseInt(data[0]), Integer.parseInt(data[1]), d, data[3]);

      // This line to test Strategy 2
      redisApi.insertTweet(recent, true);

      // This line to test Strategy 1
      // redisApi.insertTweet(recent);

    }
    csvReaderTweets.close();

    // End time of loading tweets in nanos.
    long loadStopTime = System.nanoTime();
    System.out.println("Took  " + (loadStopTime - loadStartTime) / 1000000000);


    // Some of this logic was grabbed from SO here: https://stackoverflow.com/questions/32806432/how-to-convert-resultset-to-string-in-java

    while (true) {
      // To test how long it takes to load timeline
      long startTime = System.nanoTime();
      int randomNum = ThreadLocalRandom.current().nextInt(1, 1000 + 1);

      // List<String[]> tweets = mySqlApi.getTimeline();
      // mySqlApi.printTweets(tweets);

      List<String[]> tweets = redisApi.getTimeline(randomNum);
      redisApi.printTweets(tweets);

      // End time of loading tweets.
      long stopTime = System.nanoTime();
      // Output seconds (rounded) to pull timeline
      System.out.println((stopTime - startTime) / 1000000000);
      Thread.sleep(1000);
    }
  }
}
