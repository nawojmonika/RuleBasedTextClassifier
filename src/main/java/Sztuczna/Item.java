package Sztuczna;

import Sztuczna.Algorithms.interfaces.Algorithm;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface Item {
    public String getLabelByValue(String value);
    public Item performWordsAlgorithm(Algorithm<List> algorithm);
    public List<String> getAlgorithmsWords();
    public UUID getUniqueId();
    public String getMainText();
    public Item performTermsAlgorithm(Algorithm<Map<String, Integer>> algorithm);
}
