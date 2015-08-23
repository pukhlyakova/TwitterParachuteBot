import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created by padme on 23.08.2015.
 * Main class
 */
public class Twitbot {
    public static void main(String[] args) {
        Twitter twitter = getTwitter();
        try {
            Query query = new Query();
            query.setQuery(getQueryText());
            query.setLocale("ru");
            query.setLang("ru");
            query.setResultType(Query.ResultType.mixed);
            query.setCount(100);
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {
                System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
                twitter.retweetStatus(status.getId());
            }
        } catch(TwitterException e) {
            System.out.println(e);
        }
    }

    private static Twitter getTwitter() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("*********************")
                .setOAuthConsumerSecret("*********************")
                .setOAuthAccessToken("******************************************")
                .setOAuthAccessTokenSecret("*********************");
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }

    private static String getQueryText() {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader("E:\\Projects\\TwitterParachuteBot\\src\\query.txt"))) {
            String line = br.readLine();
            sb.append(line);
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return sb.toString();
    }
}
