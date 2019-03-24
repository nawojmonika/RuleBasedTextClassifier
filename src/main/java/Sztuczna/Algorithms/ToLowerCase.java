package Sztuczna.Algorithms;

import Sztuczna.Article;

import java.util.ArrayList;
import java.util.Arrays;

import static java.util.stream.Collectors.toCollection;

public class ToLowerCase implements Algorithm {

    @Override
    public ArrayList<String> perform(Article a) {
        return a.getAlgorithmsWords()
                .stream()
                .map(String::toLowerCase)
                .collect(toCollection(ArrayList::new));
    }
}
