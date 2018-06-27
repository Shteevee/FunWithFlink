package TwitterGathering;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple4;

import java.util.ArrayList;

public class TupleConverter implements MapFunction<Tweet, Tuple4<String, ArrayList<String>, String, String>> {

    public Tuple4<String, ArrayList<String>, String, String> map(Tweet tweet) {
        return Tuple4.of(tweet.getText(), tweet.getHashtags(), tweet.getUser(), tweet.getDescription());
    }

}
