package Sztuczna.Algorithms;

import Sztuczna.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DictionaryWordsInArticle extends Property<Integer> {
    Map<String, Double> words;

    public DictionaryWordsInArticle(PropertiesManager pm) {
        super("DictionaryWordsInArticle", 0);
        this.words = pm.getWordsDictionary();
    }

    @Override
    public Integer perform(Article a) {
        ArrayList<String> wordsInArticle = a.getAlgorithmsWords();
        this.setValue((int)wordsInArticle.stream().filter(wordFromArticle -> words.containsKey(wordFromArticle)).count());
        return this.getValue();
    }
}
