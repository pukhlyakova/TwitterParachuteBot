import twitter4j.*;

import java.util.*;

/**
 * This class search last status and retweet them.
 * Created by padme on 26.08.2015.
 */
public class RetweetStatusTimer {
    private Timer timer;
    private final List<String> searchingText = new ArrayList<>();
    {
        searchingText.add("прыгнуть с парашютом но");
        searchingText.add("прыгнула с парашютом но");
        searchingText.add("прыгнул с парашютом но");
        searchingText.add("прыгать с парашютом но");
    }

    /**
     * Every minute gets status from twitter and retweet it if it was written in last minute.
     */
    public void startTimer(final Twitter twitter) {
        timer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            public void run()
            {
                retweet(twitter);
            }
        };
        timer.schedule(task, 0L, 60L * 1000);
    }

    private void retweet(final Twitter twitter) {
        Date date = Calendar.getInstance().getTime();
        //System.out.println(date.getTime());
        Query query = getQuery("");
        try {
            for (String text : searchingText) {
                query.setQuery(text);
                QueryResult result = twitter.search(query);
                for (Status status : result.getTweets()) {
                    Date statusTime = status.getCreatedAt();
                    long ms = date.getTime() - statusTime.getTime();
                    if (!status.isRetweet()) {
                        //System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
                        twitter.retweetStatus(status.getId());
                    }
                }
            }
        } catch(TwitterException e) {
            //System.out.println(e);
        }
    }

    private Query getQuery(String text) {
        Query query = new Query();
        query.setQuery(text);
        query.setLocale("ru");
        query.setLang("ru");
        query.setResultType(Query.ResultType.recent);
        return query;
    }
}
