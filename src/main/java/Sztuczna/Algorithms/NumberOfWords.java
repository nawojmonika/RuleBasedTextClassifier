package Sztuczna.Algorithms;

import Sztuczna.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NumberOfWords extends Property<Integer> {
    Map<String, Integer> words;

    public NumberOfWords(PropertiesManager pm) {
        super("NumberOfWords", 0);
        this.words = pm.getWordsDictionary();
    }

    @Override
    public Integer perform(Article a) {
        ArrayList<String> wordsInArticle = a.getAlgorithmsWords();
        this.setValue((int)wordsInArticle.stream().filter(wordFromArticle -> words.containsKey(wordFromArticle)).count());
        System.out.println(this.getValue());
        return this.getValue();
    }
}
