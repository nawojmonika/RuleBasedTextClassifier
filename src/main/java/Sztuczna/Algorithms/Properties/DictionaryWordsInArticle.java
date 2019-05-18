package Sztuczna.Algorithms.Properties;

import Sztuczna.Article;

import java.util.ArrayList;
import java.util.Map;

public class DictionaryWordsInArticle extends Property<Double> {
    Map<String, Double> words;

    public DictionaryWordsInArticle(PropertiesManager pm) {
        super("DictionaryWordsInArticle", 0.0);
        this.words = pm.getWordsDictionary();
    }

    @Override
    public Double perform(Article a) {
        ArrayList<String> wordsInArticle = a.getAlgorithmsWords();
        this.setValue(new Double((int)wordsInArticle.stream().filter(wordFromArticle -> words.containsKey(wordFromArticle)).count()));
        return this.getValue();
    }
}