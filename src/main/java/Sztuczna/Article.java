package Sztuczna;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Article {
    private String title;
    private String body;
    private String oldId;
    private String countryLabel;
    private ArrayList<String> tokenizedWords;

    public Article(String title, String body, String oldId, String countryLabel) {
        this.title = title;
        this.body = body;
        this.oldId = oldId;
        this.countryLabel = countryLabel;
        this.tokenizedWords = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getOldId() {
        return oldId;
    }

    public String getCountryLabel() {
        return countryLabel;
    }

    public void tokenizeWords() {
        Pattern wordPatern = Pattern.compile("(\\w+)");
        Matcher wordsMatcher = wordPatern.matcher(body);

        while (wordsMatcher.find()) {
            System.out.println(wordsMatcher.group(1));
            tokenizedWords.add(wordsMatcher.group(1));
        }
    }
}
