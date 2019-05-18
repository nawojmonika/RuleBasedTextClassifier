package Sztuczna;

import Sztuczna.Algorithms.interfaces.Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Article {
    private String title;
    private String body;
    private String oldId;
    private UUID uniqueId;
    private String countryLabel;

    private ArrayList<String> algorithmsWords = new ArrayList<>();

    public Map<String, Integer> getTermWords() {
        return termWords;
    }

    private Map<String, Integer> termWords = new HashMap<>();

    public Article(String title, String body, String oldId, String countryLabel) {
        this.title = title;
        this.body = body;
        this.oldId = oldId;
        this.uniqueId = UUID.randomUUID();
        this.countryLabel = countryLabel;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getOldId() {
        return oldId;
    }
    public UUID getUniqueId() { return this.uniqueId; }

    public String getCountryLabel() {
        return countryLabel;
    }

    public ArrayList<String> getAlgorithmsWords() {
        return this.algorithmsWords;
    }

    public Article performWordsAlgorithm(Algorithm<ArrayList> algorithm) {
        this.algorithmsWords = algorithm.perform(this);
        return this;
    }
    public Article performTermsAlgorithm(Algorithm<Map<String, Integer>> algorithm) {
        this.termWords = algorithm.perform(this);
        return this;
    }

    public void tokenizeWords() {
    }
}
