package Sztuczna.Algorithms;

import Sztuczna.Algorithms.interfaces.Algorithm;
import Sztuczna.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TerminizeWords implements Algorithm {

    @Override
    public Map<String, Integer> perform(Item a) {
        Map<String, Integer> termWords = new HashMap<>();

        for (String word : a.getAlgorithmsWords()) {
            termWords.put(word, a.getAlgorithmsWords()
                    .stream()
                    .filter(s -> s.compareTo(word) == 0)
                    .collect(Collectors.reducing(0, e -> 1, Integer::sum)));
        }
        return termWords;
    }
}
