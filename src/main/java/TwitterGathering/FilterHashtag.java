package TwitterGathering;

import org.apache.flink.api.common.functions.FilterFunction;

public class FilterHashtag implements FilterFunction<Tweet> {
    @Override
    public boolean filter(Tweet tweet) {
        return tweet.getHashtags().contains("hashtagExample");
    }
}
