package Sztuczna.Algorithms;

import Sztuczna.Article;

import java.util.ArrayList;
import java.util.Map;

public class FirstDictionaryWordInArticle extends Property<Integer> {
    Map<String, Double> words;

    public FirstDictionaryWordInArticle(PropertiesManager pm) {
        super("DictionaryWordsInArticle", 0);
        this.words = pm.getWordsDictionary();
    }

    @Override
    public Integer perform(Article a) {
        ArrayList<String> wordsInArticle = a.getAlgorithmsWords();
        int positionOfTheFirstKeyWord = Integer.MAX_VALUE;
        for (Map.Entry<String, Double> dictionaryWord : words.entrySet()) {
            int currentPosition = 1;
            for (String word : wordsInArticle) {
                if (word.compareTo(dictionaryWord.getKey()) == 0) {
                    if (currentPosition < positionOfTheFirstKeyWord) {
                        positionOfTheFirstKeyWord = currentPosition;
                    }
                    break;
                }
                currentPosition++;
            }

        }
        this.setValue(positionOfTheFirstKeyWord);
        return this.getValue();
    }
}
