package Sztuczna.Algorithms;

import Sztuczna.Article;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumOfWordsDefinedByUser extends Property<Integer> {
    Set<String> wordsDefinedByUser = new HashSet<>();

    public NumOfWordsDefinedByUser(PropertiesManager pm) {
        super("NumOfWordsDefinedByUser", 0);
    }

    public void setWordsDefinedByUser(Set<String> wordsDefinedByUser) {
        wordsDefinedByUser = wordsDefinedByUser;
    }

    @Override
    public Integer perform(Article a) {
        ArrayList<String> wordsInArticle = a.getAlgorithmsWords();
        this.setValue((int)wordsInArticle.stream().filter(wordFromArticle -> wordsDefinedByUser.contains(wordFromArticle)).count());
        return this.getValue();
    }
}
