package TwitterGathering;

import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.twitter.TwitterSource;

import java.util.Properties;

public class TweetGathering {

    public static void main(String args[]) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Keys keys = new Keys();

        Properties props = new Properties();
        props.setProperty(TwitterSource.CONSUMER_KEY, keys.getConsumerKey());
        props.setProperty(TwitterSource.CONSUMER_SECRET, keys.getConsumerSecret());
        props.setProperty(TwitterSource.TOKEN, keys.getToken());
        props.setProperty(TwitterSource.TOKEN_SECRET, keys.getTokenSecret());

        env.addSource(new TwitterSource(props))
                .flatMap(new StripTweet())
                .filter(new FilterLanguage())
                // .filter(new FilterHashtag())
                .map(new TupleConverter())
                .writeAsCsv("tweetData", FileSystem.WriteMode.OVERWRITE, "\n", ",");

        env.execute();
    }
}
