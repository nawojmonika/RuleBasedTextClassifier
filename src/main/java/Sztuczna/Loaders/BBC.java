package Sztuczna.Loaders;

import Sztuczna.Algorithms.interfaces.Algorithm;
import Sztuczna.Item;

import java.util.*;

public class BBC implements Item {
    private UUID uniqueId;


    private String content;
    private String category;

    private List<String> algorithmsWords = new ArrayList<>();
    public Map<String, Integer> getTermWords() {
        return termWords;
    }
    private Map<String, Integer> termWords = new HashMap<>();

    public BBC(String content, String category) {
        this.uniqueId = UUID.randomUUID();
        this.content = content;
        this.category = category;
    }

    public UUID getUniqueId() { return this.uniqueId; }

    public List<String> getAlgorithmsWords() {
        return this.algorithmsWords;
    }

    public BBC performWordsAlgorithm(Algorithm<List> algorithm) {
        this.algorithmsWords = algorithm.perform(this);
        return this;
    }

    public String getLabelByValue(String value) {
        if (value.compareTo("content") == 0) {
            return this.getContent();
        } else if (value.compareTo("category") == 0) {
            return this.getCategory();
        }
        throw new Error("Wrong label");
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String getMainText() {
        return this.getContent();
    }

    public BBC performTermsAlgorithm(Algorithm<Map<String, Integer>> algorithm) {
        this.termWords = algorithm.perform(this);
        return this;
    }
}
