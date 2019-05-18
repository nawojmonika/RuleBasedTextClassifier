package Sztuczna.Algorithms;

import Sztuczna.Article;

import java.util.ArrayList;
import java.util.Map;

public class FrequencyOfDictionaryWords extends Property<Double> {
    Map<String, Double> words;
    PropertiesManager pm;

    public FrequencyOfDictionaryWords(PropertiesManager pm) {
        super("FrequencyOfDictionaryWords", 0.0);
        this.words = pm.getWordsDictionary();
        this.pm = pm;
    }

    @Override
    public Double perform(Article a) {
        ArrayList<String> wordsInArticle = a.getAlgorithmsWords();
        Double numOfWordsInArticleAndDictionary = new DictionaryWordsInArticle(pm).perform(a);
        Double numOfWordsInArticle = new NumberOfWordsInArticle(pm).perform(a);
        this.setValue((double)numOfWordsInArticleAndDictionary / (double)numOfWordsInArticle);
        return this.getValue();
    }
}
