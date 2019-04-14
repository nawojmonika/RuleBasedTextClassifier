package Sztuczna.Metrics;

public class ExtendedNGramSimilarity implements TextSimilarityMetric {
    public Double calculateSimilarity(String prop1, String prop2) {
        int N = Math.max(prop1.length(), prop2.length());
        Double sum = 0.0;
        prop1.toLowerCase();
        prop2.toLowerCase();
        for (int i = 0; i < N ; i++) {
            for (int j = 0; j < N - i + 1 ; j++) {
                String substring = prop1.substring(i, i + i + j);
                sum += prop2.indexOf(substring) != -1 ? 1 : 0;
            }
        }
        Double sim = (2 / Math.pow(N,2) + N) * sum;
        return sim;
    }
}