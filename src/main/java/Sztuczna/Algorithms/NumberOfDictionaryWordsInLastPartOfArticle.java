package Sztuczna.Algorithms;

import Sztuczna.Article;

import java.util.ArrayList;
import java.util.Map;

public class NumberOfDictionaryWordsInLastPartOfArticle extends Property<Integer> {
    Map<String, Double> words;

    public NumberOfDictionaryWordsInLastPartOfArticle(PropertiesManager pm) {
        super("NumberOfDictionaryWordsInFirstPartOfArticle", 0);
        this.words = pm.getWordsDictionary();
    }

    @Override
    public Integer perform(Article a) {
        ArrayList<String> wordsInArticle = a.getAlgorithmsWords();
        final int PERCENT_OF_FIRST_PART = 20;
        final int NUMBER_OF_WORDS_TO_CHECK = Math.round((PERCENT_OF_FIRST_PART * wordsInArticle.size())/100);

        int numberOfFoundWords = 0;
        for (int currentWordIndex = wordsInArticle.size() - 1; currentWordIndex > NUMBER_OF_WORDS_TO_CHECK ; currentWordIndex-- ) {
            if (this.words.containsKey(wordsInArticle.get(currentWordIndex))) {
                numberOfFoundWords++;
            }
        }
        this.setValue(numberOfFoundWords);
        return this.getValue();
    }
}
