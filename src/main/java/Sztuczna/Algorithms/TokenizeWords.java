package Sztuczna.Algorithms;

import Sztuczna.Algorithms.interfaces.Algorithm;
import Sztuczna.Article;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenizeWords implements Algorithm {



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
