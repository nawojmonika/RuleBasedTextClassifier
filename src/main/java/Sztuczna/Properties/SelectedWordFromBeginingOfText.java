package Sztuczna.Properties;

import Sztuczna.Item;

import java.util.List;
import java.util.Map;

public class SelectedWordFromBeginingOfText extends Property<Double> {
    Map<String, Double> words;
    private String selectedText = "";

    public SelectedWordFromBeginingOfText(PropertiesManager pm) {
        super("SelectedWordFromBeginingOfText", 0.0);
        this.words = pm.getWordsDictionary();
    }

    public void setSelectedText(String selectedText) {
        this.selectedText = selectedText;
        this.setValue(0.0);
    }

    public String toString() {
        return  super.toString() + " Selected word: " + this.selectedText;
    }

    @Override
    public Double perform(Item a) {
        List<String> wordsInArticle = a.getAlgorithmsWords();
        for (int lenghtFromBeginingOfText = 0 ; lenghtFromBeginingOfText < wordsInArticle.size() ; lenghtFromBeginingOfText++) {
            if (selectedText == wordsInArticle.get(lenghtFromBeginingOfText)) {
                this.setValue( new Double(lenghtFromBeginingOfText));
                break;
            }
        }
        return this.getValue();
    }
}
