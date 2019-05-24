package Sztuczna.Algorithms;

import Sztuczna.Algorithms.interfaces.Algorithm;
import Sztuczna.Article;
import Sztuczna.Item;

import java.util.ArrayList;

import static java.util.stream.Collectors.toCollection;

public class ToLowerCase implements Algorithm {

    @Override
    public ArrayList<String> perform(Item a) {
        return a.getAlgorithmsWords()
                .stream()
                .map(String::toLowerCase)
                .collect(toCollection(ArrayList::new));
    }
}
