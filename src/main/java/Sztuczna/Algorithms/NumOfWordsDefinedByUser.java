package Sztuczna.Algorithms;

import Sztuczna.Article;

import java.util.*;
import java.util.stream.Collectors;

public class NumOfWordsDefinedByUser extends Property<Integer> {
    Set<String> wordsDefinedByUser = new HashSet<>();

    public NumOfWordsDefinedByUser(PropertiesManager pm) {
        super("NumOfWordsDefinedByUser", 0);
    }

    public void setWordsDefinedByUser(Set<String> wordsDefinedByUser) {
        this.wordsDefinedByUser = wordsDefinedByUser;
    }

    @Override
    public Integer perform(Article a) {
        ArrayList<String> wordsInArticle = a.getAlgorithmsWords();
        this.setValue(wordsInArticle
                .stream()
                .filter(wordFromArticle -> wordsDefinedByUser.contains(wordFromArticle))
                .collect(Collectors.reducing(0, e -> 1, Integer::sum)));
        return this.getValue();
    }
}
