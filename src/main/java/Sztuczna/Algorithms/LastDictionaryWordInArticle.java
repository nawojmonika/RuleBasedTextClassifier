package Sztuczna.Algorithms;

import Sztuczna.Article;

import java.util.ArrayList;
import java.util.Map;

public class LastDictionaryWordInArticle extends Property<Integer> {
    Map<String, Double> words;

    public LastDictionaryWordInArticle(PropertiesManager pm) {
        super("LastDictionaryWordInArticle", 0);
        this.words = pm.getWordsDictionary();
    }

    @Override
    public Integer perform(Article a) {
        ArrayList<String> wordsInArticle = a.getAlgorithmsWords();
        int positionOfTheFirstKeyWord = -1;
        for (Map.Entry<String, Double> dictionaryWord : words.entrySet()) {
            for (int currentPosition = wordsInArticle.size() - 1; currentPosition >= 0; currentPosition--) {
                if (wordsInArticle.get(currentPosition).compareTo(dictionaryWord.getKey()) == 0) {
                    if (currentPosition > positionOfTheFirstKeyWord) {
                        positionOfTheFirstKeyWord = currentPosition;
                    }
                    break;
                }

            }

        }
        this.setValue(positionOfTheFirstKeyWord);
        return this.getValue();
    }
}
