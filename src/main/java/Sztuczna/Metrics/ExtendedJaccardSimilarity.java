package Sztuczna.Metrics;

public class ExtendedJaccardSimilarity implements TextSimilarityMetric {
    public Double calculateSimilarity(String prop1, String prop2) {
        int n = 2; // because text is in english
        int N = Math.max(prop1.length(), prop2.length());
        Double common = 0.0;
        Double unshared = 0.0;
        prop1.toLowerCase();
        prop2.toLowerCase();
        for (int i = 0; i < N ; i++) {
            for (int j = 0; j < N - i + 1 ; j++) {
                String substring = prop1.substring(i, i + n);
                if(prop2.indexOf(substring) != -1){
                    common += 1;
                }
                else {
                    unshared += 1;
                }
            }
        }
        Double sim = (common + unshared) / N;
        return sim;
    }
}
