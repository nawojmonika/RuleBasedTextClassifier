package Sztuczna.Algorithms;

import Sztuczna.Article;

import java.util.ArrayList;
import java.util.Map;

public class SelectedWordFromBeginingOfText extends Property<Integer> {
    Map<String, Integer> words;
    private String selectedText = "";

    public SelectedWordFromBeginingOfText(PropertiesManager pm) {
        super("SelectedWordFromBeginingOfText", 0);
        this.words = pm.getWordsDictionary();
    }

    public void setSelectedText(String selectedText) {
        this.selectedText = selectedText;
        this.setValue(0);
    }

    public String toString() {
        return  super.toString() + " Selected word: " + this.selectedText;
    }

    @Override
    public Integer perform(Article a) {
        ArrayList<String> wordsInArticle = a.getAlgorithmsWords();
        for (int lenghtFromBeginingOfText = 0 ; lenghtFromBeginingOfText < wordsInArticle.size() ; lenghtFromBeginingOfText++) {
            if (selectedText == wordsInArticle.get(lenghtFromBeginingOfText)) {
                this.setValue(lenghtFromBeginingOfText);
                break;
            }
        }
        return this.getValue();
    }
}
