package Sztuczna.Algorithms;

import Sztuczna.Algorithms.interfaces.Algorithm;
import Sztuczna.Article;
import Sztuczna.Item;
import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.englishStemmer;

import java.util.ArrayList;

import static java.util.stream.Collectors.toCollection;

public class Stemization implements Algorithm {

    @Override
    public ArrayList<String> perform(Item a) {
        return a.getAlgorithmsWords()
                .stream()
                .map(Stemization::stemization)
                .collect(toCollection(ArrayList::new));
    }

    public static String stemization(String word) {
        SnowballStemmer stemer = new englishStemmer();

        stemer.setCurrent(word);
        stemer.stem();

        return stemer.getCurrent();
    }

}
