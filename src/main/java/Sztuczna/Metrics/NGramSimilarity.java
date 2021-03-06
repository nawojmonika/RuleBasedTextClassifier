package Sztuczna.Metrics;

import Sztuczna.Metrics.interfaces.TextSimilarityMetric;

public class NGramSimilarity implements TextSimilarityMetric {
    public Double calculateSimilarity(String prop1, String prop2) {
        int n = 2; // because text is in english
        int N = Math.max(prop1.length(), prop2.length());
        Double sum = 0.0;
        prop1 = prop1.toLowerCase();
        prop2 = prop2.toLowerCase();

        for (int i = 0; i < prop1.length() - n ; i++) {
            String substring = prop1.substring(i, i + n);
            sum += prop2.indexOf(substring) != -1 ? 1 : 0;
        }
        Double sim = sum / (N - n + 1);
        return sim;
    }
}
