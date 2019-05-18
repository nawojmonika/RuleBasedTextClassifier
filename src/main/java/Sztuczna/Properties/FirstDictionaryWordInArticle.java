package Sztuczna.Properties;

import Sztuczna.Article;

import java.util.ArrayList;
import java.util.Map;

public class FirstDictionaryWordInArticle extends Property<Double> {
    Map<String, Double> words;

    public FirstDictionaryWordInArticle(PropertiesManager pm) {
        super("DictionaryWordsInArticle", 0.0);
        this.words = pm.getWordsDictionary();
    }

    @Override
    public Double perform(Article a) {
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
        this.setValue(new Double(positionOfTheFirstKeyWord));
        return this.getValue();
    }
}
