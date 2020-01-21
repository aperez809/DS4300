package API;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Twitter {
    private static TFDatabaseAPI api = new TFDatabaseMysql();

    public static void main(String[] args) throws Exception {
      BufferedReader csvReaderTweets = new BufferedReader(new FileReader("/Users/owen/opt/ds4300/DS4300/HW1/tweets.csv"));
      BufferedReader csvReaderFollowers = new BufferedReader(new FileReader("/Users/owen/opt/ds4300/DS4300/HW1/user_followers.csv"));

      String row = "";


      // Follower table load
      while ((row = csvReaderFollowers.readLine()) != null) {
        String[] data = row.split(",");
        Follower f = new Follower(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
        api.registerFollower(f);
      }
      csvReaderFollowers.close();

      // To test how long it takes to load tweets
      long startTime = System.nanoTime();
      System.out.println("Starting Tweets Load");

      // Tweet table load
      while ((row = csvReaderTweets.readLine()) != null) {
            String[] data = row.split(",");
            Date d = null;
            try {
              d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse(data[2]);
            } catch (java.text.ParseException e) {
              d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data[2]);
        }
            Tweet recent = new Tweet(Integer.parseInt(data[0]), Integer.parseInt(data[1]), d, data[3]);
            api.insertTweet(recent);
        }
        csvReaderTweets.close();

      // End time of loading tweets in nanos.
      long stopTime = System.nanoTime();
      System.out.println(stopTime - startTime);

    }
}
