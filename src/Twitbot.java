import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;


/**
 * Created by padme on 23.08.2015.
 * Main class
 */
public class Twitbot {
    public static void main(String[] args) {
        Twitbot twitbot = new Twitbot();
        RetweetStatusTimer statusTimer = new RetweetStatusTimer();
        Twitter twitter = twitbot.getTwitterSetting();
        statusTimer.startTimer(twitter);
    }

    private Twitter getTwitterSetting() {
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
