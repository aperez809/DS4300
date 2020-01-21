package API;

public class Twitter {
    private static TFDatabaseAPI api = new TFDatabaseMysql();

    public static void main(String[] args){
        BufferedReader csvReader = new BufferedReader(new FileReader(tweets.csv))
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            Tweet recent = new Tweet(data[0], data[1], data[2], data[3]);
            insertTweet(recent);
        }
        csvReader.close();
    }
}
