package TwitterGathering;

import org.apache.flink.api.common.functions.FilterFunction;

public class FilterLanguage implements FilterFunction<Tweet> {
    @Override
    public boolean filter(Tweet tweet) {
        return tweet.getLanguage().equals("en");
    }
}
