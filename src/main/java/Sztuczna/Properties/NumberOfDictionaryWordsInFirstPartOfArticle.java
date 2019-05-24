package Sztuczna.Properties;

import Sztuczna.Item;

import java.util.List;
import java.util.Map;

public class NumberOfDictionaryWordsInFirstPartOfArticle extends Property<Double> {
    Map<String, Double> words;

    public NumberOfDictionaryWordsInFirstPartOfArticle(PropertiesManager pm) {
        super("NumberOfDictionaryWordsInFirstPartOfArticle", 0.0);
        this.words = pm.getWordsDictionary();
    }

    @Override
    public Double perform(Item a) {
        List<String> wordsInArticle = a.getAlgorithmsWords();
        final int PERCENT_OF_FIRST_PART = 20;
        final int NUMBER_OF_WORDS_TO_CHECK = Math.round((PERCENT_OF_FIRST_PART * wordsInArticle.size())/100);

        int numberOfFoundWords = 0;
        for (int currentWordIndex = 0; currentWordIndex < NUMBER_OF_WORDS_TO_CHECK ; currentWordIndex++ ) {
            if (this.words.containsKey(wordsInArticle.get(currentWordIndex))) {
                numberOfFoundWords++;
            }
        }
        this.setValue(new Double(numberOfFoundWords));
        return this.getValue();
    }
}
