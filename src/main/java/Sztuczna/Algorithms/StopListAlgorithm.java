package Sztuczna.Algorithms;

import Sztuczna.Article;

import java.util.ArrayList;
import java.util.Arrays;

import static java.util.stream.Collectors.toCollection;

public class StopListAlgorithm implements Algorithm {

    ArrayList<String> stopList = new ArrayList<>( Arrays.asList(
            "the",
            "in",
            "since",
            "and",
            "for"
    ));

    @Override
    public ArrayList<String> perform(Article a) {
        return a.getAlgorithmsWords()
                .stream()
                .filter(word -> !this.stopList.contains(word))
                .collect(toCollection(ArrayList::new));
    }
}
