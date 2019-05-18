package Sztuczna.Metrics;

import Sztuczna.Metrics.interfaces.TextSimilarityMetric;

public class SimpleStringCompare implements TextSimilarityMetric {
    public Double calculateSimilarity(String prop1, String prop2) {
        return prop1 == prop2 ? 0.0 : 1;
    }
}
