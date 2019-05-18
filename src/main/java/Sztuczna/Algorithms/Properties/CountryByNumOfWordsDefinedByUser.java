package Sztuczna.Algorithms.Properties;

import Sztuczna.Article;

import java.util.*;
import java.util.stream.Collectors;

public class CountryByNumOfWordsDefinedByUser extends Property<String> {
    Set<String> wordsDefinedByUser = new HashSet<>();
    Map<String, ArrayList<String>> wordsByCountry = null;

    public CountryByNumOfWordsDefinedByUser(PropertiesManager pm, Map<String, ArrayList<String>> wordsByCountry) {
        super("NumOfWordsDefinedByUser", "");
        this.wordsByCountry = wordsByCountry;
    }

    public void setWordsDefinedByUser(Set<String> wordsDefinedByUser) {
        this.wordsDefinedByUser = wordsDefinedByUser;
    }

    @Override
    public String perform(Article a) {
        ArrayList<String> wordsInArticle = a.getAlgorithmsWords();
        Map<String, Integer> numberOfFoundsForCountry = new LinkedHashMap<>();
        for (Map.Entry<String, ArrayList<String>> wordsForCountry : wordsByCountry.entrySet()) {
            numberOfFoundsForCountry.put(wordsForCountry.getKey(), wordsInArticle
                .stream()
                .filter(wordFromArticle -> wordsForCountry.getValue().contains(wordFromArticle))
                .collect(Collectors.reducing(0, e -> 1, Integer::sum)));
        }
        Map.Entry<String, Integer> maxOccurence = numberOfFoundsForCountry.entrySet().stream().max(Map.Entry.comparingByValue()).get();
        if (maxOccurence.getValue() == 0) {
            this.setValue("Not found");
        } else {
            this.setValue(maxOccurence.getKey());
        }
        return this.getValue();
    }
}
