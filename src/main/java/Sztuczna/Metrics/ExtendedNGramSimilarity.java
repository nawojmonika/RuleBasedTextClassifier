package Sztuczna.Metrics;

import Sztuczna.Metrics.interfaces.TextSimilarityMetric;

public class ExtendedNGramSimilarity implements TextSimilarityMetric {
    public Double calculateSimilarity(String prop1, String prop2) {
        int N = Math.max(prop1.length(), prop2.length());
        int n = prop1.length();
        Double sum = 0.0;
        prop1 = prop1.toLowerCase();
        prop2 = prop2.toLowerCase();
        for (int i = 0; i < n + 1; i++) {
            for (int j = 1; j < n - i + 1; j++) {
                String substring = prop1.substring(i, i + j);
                sum += prop2.indexOf(substring) != -1 ? 1 : 0;
            }
        }

        Double sim = (2 * sum) / (Math.pow(N,2) + N);
        return sim;
    }
}
