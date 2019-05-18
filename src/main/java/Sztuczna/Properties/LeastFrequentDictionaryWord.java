package Sztuczna.Properties;

import Sztuczna.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class LeastFrequentDictionaryWord extends Property<String> {
    Map<String, Double> words;
    private String selectedText = "";

    public LeastFrequentDictionaryWord(PropertiesManager pm) {
        super("MostFrequentDictionaryWord", "null");
        this.words = pm.getWordsDictionary();
    }


    @Override
    public String perform(Article a) {
        ArrayList<String> wordsInArticle = a.getAlgorithmsWords();
        Map<String, Integer> frequentWords = new HashMap<>();

        for (String word : wordsInArticle) {
            if (this.words.containsKey(word)) {
                if (frequentWords.containsKey(word)) {
                    frequentWords.put(word, frequentWords.get(word) + 1);
                } else {
                    frequentWords.put(word, 0);
                }
            }
        }
        AtomicReference<String> mostFrequentWord = new AtomicReference<>("");
        Integer frequency = Integer.MAX_VALUE;
        frequentWords.entrySet().stream().forEach(stringIntegerEntry -> {
            if (frequency > stringIntegerEntry.getValue())   {
                mostFrequentWord.set(stringIntegerEntry.getKey());
            }
        });
        this.setValue(mostFrequentWord.get());
        return this.getValue();
    }
}
