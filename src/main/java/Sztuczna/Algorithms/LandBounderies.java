package Sztuczna.Algorithms;

import Sztuczna.Algorithms.Properties.PropertiesManager;
import Sztuczna.Algorithms.Properties.Property;
import Sztuczna.Article;

import java.util.ArrayList;
import java.util.Map;

public class LandBounderies extends Property<Double> {
    Map<String, Double> words;

    public LandBounderies(PropertiesManager pm) {
        super("NumberOfWordsInArticle", 0.0);
        this.words = pm.getWordsDictionary();
    }

    @Override
    public Double perform(Article a) {
        ArrayList<String> wordsInArticle = a.getAlgorithmsWords();
        this.setValue(new Double((int)wordsInArticle.stream().count()));
        return this.getValue();
    }
}
