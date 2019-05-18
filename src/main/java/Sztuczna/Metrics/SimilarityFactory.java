package Sztuczna.Metrics;

import Sztuczna.Metrics.interfaces.TextSimilarityMetric;

public class SimilarityFactory {
    public static TextSimilarityMetric build(String metric) {
        if (metric.compareTo("ExtendedNGramSimilarity") == 0) {
            return new ExtendedNGramSimilarity();
        } else if (metric.compareTo("JaccardSimilarity") == 0) {
            return new JaccardSimilarity();
        } else if (metric.compareTo("NGramSimilarity") == 0) {
            return new NGramSimilarity();
        } else if (metric.compareTo("SimpleStringCompare") == 0) {
            return new SimpleStringCompare();
        }
        return new NGramSimilarity();
    }
}
