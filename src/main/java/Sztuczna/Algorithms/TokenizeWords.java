package Sztuczna.Algorithms;

import Sztuczna.Article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toCollection;

public class TokenizeWords implements Algorithm {

    ArrayList<String> stopList = new ArrayList<>( Arrays.asList(
            "the",
            "in",
            "since",
            "and",
            "for"
    ));

    @Override
    public ArrayList<String> perform(Article a) {
        Pattern wordPatern = Pattern.compile("(\\w+)");
        Matcher wordsMatcher = wordPatern.matcher(a.getBody());
        ArrayList<String> tokenizedWords = new ArrayList<>();

        while (wordsMatcher.find()) {
            tokenizedWords.add(wordsMatcher.group(1));
        }

        return tokenizedWords;
    }
}
