package API;

import java.util.Date;


public class Tweet {

  // Number of fields to return in the result set for each Tweet
  public static final int NUM_FIELDS = 4;

  private int id;
  private int tweetId;
  private int userId;
  private Date tweetTS;
  private String tweetText;

  public String toString() { return this.tweetId + ":" + this.userId + ":" + this.tweetTS + ":" + this.tweetText; }


  public Tweet(int id, int tweetId, int userId, Date tweetTS, String tweetText){
      this.id = id;
      this.tweetId = tweetId;
      this.userId = userId;
      this.tweetTS = tweetTS;
      this.tweetText = tweetText;
  }

  public Tweet(int tweetId, int userId, Date tweetTS, String tweetText){
      this.tweetId = tweetId;
      this.userId = userId;
      this.tweetTS = tweetTS;
      this.tweetText = tweetText;
  }



  public int getId(){return id;}
  public void setId(int id){this.id = id;}

  public int getTweetId(){return tweetId;}
  public void setTweetId(int tweetId){this.tweetId = tweetId;}

  public int getUserId(){return userId;}
  public void setUserId(int userId){this.userId = userId;}

  public Date getTweetTS(){return tweetTS;}
  public void setTweetTS(Date tweetTS){this.tweetId = tweetId;}

  public String getTweetText(){return tweetText;}
  public void setTweetText(String tweetText){this.tweetText = tweetText;}

}
