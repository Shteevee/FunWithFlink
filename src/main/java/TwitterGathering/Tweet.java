package TwitterGathering;

import java.util.ArrayList;

public class Tweet {

    private String text;
    private String language;
    private ArrayList<String> hashtags;
    private String user;
    private String description;


    public Tweet(String text, String language, ArrayList<String> hashtags, String user, String description) {
        this.text = text;
        this.language = language;
        this.hashtags = hashtags;
        this.user = user;
        this.description = description;
    }

    public String getText() {

        return text;
    }

    public String getLanguage() {

        return language;
    }

    public ArrayList<String> getHashtags() {

        return hashtags;
    }

    public String getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return "Tweet: \n" +
                "Text: " + text + "\n" +
                "Lang: " + language + "\n" +
                "Hashtags: " + hashtags + "\n" +
                "User: " + user + "\n" +
                "Description: " + description + "\n";
    }
}
