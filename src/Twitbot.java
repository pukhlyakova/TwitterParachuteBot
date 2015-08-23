import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by padme on 23.08.2015.
 * Main class
 */
public class Twitbot {
    public static void main(String[] args) {
        Twitter twitter = getTwitter();
        try {
            Status status = twitter.updateStatus("My second twit");
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
}
