package Sztuczna.Metrics;

public class NGramSimilarity implements TextSimilarityMetric {
    public Double calculateSimilarity(String prop1, String prop2) {
        int n = 2; // because text is in english
        int N = Math.max(prop1.length(), prop2.length());
        Double sum = 0.0;
        prop1.toLowerCase();
        prop2.toLowerCase();
        for (int i = 0; i < N - n + 1; i++) {
            sum += prop1.substring(i, i + n) == prop2.substring(i, i + n) ? 1 : 0;
        }
        Double sim = (1 / N - n + 1) * sum;
        return sim;
    }
}
