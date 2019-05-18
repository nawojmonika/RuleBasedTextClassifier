package Sztuczna.Algorithms.Properties;

import Sztuczna.Article;

import java.util.ArrayList;
import java.util.Map;

public class NumberOfDictionaryWordsInLastPartOfArticle extends Property<Double> {
    Map<String, Double> words;

    public NumberOfDictionaryWordsInLastPartOfArticle(PropertiesManager pm) {
        super("NumberOfDictionaryWordsInFirstPartOfArticle", 0.0);
        this.words = pm.getWordsDictionary();
    }

    @Override
    public Double perform(Article a) {
        ArrayList<String> wordsInArticle = a.getAlgorithmsWords();
        final int PERCENT_OF_FIRST_PART = 20;
        final int NUMBER_OF_WORDS_TO_CHECK = Math.round((PERCENT_OF_FIRST_PART * wordsInArticle.size())/100);

        int numberOfFoundWords = 0;
        for (int currentWordIndex = wordsInArticle.size() - 1; currentWordIndex > NUMBER_OF_WORDS_TO_CHECK ; currentWordIndex-- ) {
            if (this.words.containsKey(wordsInArticle.get(currentWordIndex))) {
                numberOfFoundWords++;
            }
        }
        this.setValue(new Double(numberOfFoundWords));
        return this.getValue();
    }
}