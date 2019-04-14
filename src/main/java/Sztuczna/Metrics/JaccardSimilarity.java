package Sztuczna.Metrics;

public class JaccardSimilarity implements TextSimilarityMetric {
    public Double calculateSimilarity(String prop1, String prop2) {
        int n = 2; // because text is in english
        int N = Math.max(prop1.length(), prop2.length());
        Double common = 0.0;
        Double unshared = 0.0;
        prop1.toLowerCase();
        prop2.toLowerCase();
        for (int i = 0; i < N - n + 1 ; i++) {
            String substring1 = prop1.substring(i, i + n);
            String substring2 = prop2.substring(i, i + n);
            unshared += prop2.indexOf(substring1) != -1 ? 0 : 1;
            unshared += prop1.indexOf(substring2) != -1 ? 0 : 1;
            if(prop2.indexOf(substring1) != -1){
                common += 1;
            }
        }
        Double sim = unshared / common;
        return sim;
    }
}
