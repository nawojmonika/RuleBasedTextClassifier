package Sztuczna.Algorithms;

import Sztuczna.Item;
import Sztuczna.Properties.PropertiesManager;
import Sztuczna.Properties.Property;

import java.util.List;
import java.util.Map;

public class LandBounderies extends Property<Double> {
    Map<String, Double> words;

    public LandBounderies(PropertiesManager pm) {
        super("NumberOfWordsInArticle", 0.0);
        this.words = pm.getWordsDictionary();
    }

    @Override
    public Double perform(Item a) {
        List<String> wordsInArticle = a.getAlgorithmsWords();
        this.setValue(new Double((int)wordsInArticle.stream().count()));
        return this.getValue();
    }
}
