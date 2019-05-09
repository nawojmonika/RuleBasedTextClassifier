package Sztuczna.Algorithms;

import Sztuczna.Article;

import java.util.ArrayList;
import java.util.Map;

public class LandBounderies extends Property<Integer> {
    Map<String, Double> words;

    public LandBounderies(PropertiesManager pm) {
        super("NumberOfWordsInArticle", 0);
        this.words = pm.getWordsDictionary();
    }

    @Override
    public Integer perform(Article a) {
        ArrayList<String> wordsInArticle = a.getAlgorithmsWords();
        this.setValue((int)wordsInArticle.stream().count());
        return this.getValue();
    }
}
