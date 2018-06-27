package TwitterGathering;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.apache.flink.util.Collector;

import java.util.Iterator;
import java.util.ArrayList;

public class StripTweet implements FlatMapFunction<String, Tweet> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void flatMap(String tweetJsonStr, Collector<Tweet> collector) throws Exception {
        JsonNode tweetJson = mapper.readTree(tweetJsonStr);
        JsonNode entities = tweetJson.get("entities");
        if (entities == null) return;

        String text = tweetJson.get("text").getTextValue();
        String lang = tweetJson.get("lang").getTextValue();
        String user = tweetJson.get("user").get("name").getTextValue();
        String desc = tweetJson.get("user").get("description").getTextValue();
        if (desc == null){
            desc = "";
        }
        ArrayList<String> hashtags = new ArrayList<>();

        JsonNode rawHashtags = entities.get("hashtags");

        for (Iterator<JsonNode> iter = rawHashtags.getElements(); iter.hasNext();){
            JsonNode node = iter.next();
            String hashtag = node.get("text").getTextValue();

            if (hashtag.matches("\\w+")) {
                hashtags.add(hashtag);
            }
        }
        collector.collect(new Tweet(text, lang, hashtags, user, desc));
    }
}
