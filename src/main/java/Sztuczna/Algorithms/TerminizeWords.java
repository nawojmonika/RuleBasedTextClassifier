package Sztuczna.Algorithms;

import Sztuczna.Article;
import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.englishStemmer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

public class TerminizeWords implements Algorithm {

    @Override
    public Map<String, Integer> perform(Article a) {
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
