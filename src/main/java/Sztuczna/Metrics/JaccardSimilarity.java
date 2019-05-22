package Sztuczna.Metrics;

import Sztuczna.Metrics.interfaces.TextSimilarityMetric;

public class JaccardSimilarity implements TextSimilarityMetric {
    public Double calculateSimilarity(String prop1, String prop2) {
        int n = 2; // because text is in english
        int N = Math.max(prop1.length(), prop2.length());
        Double common = 0.0;
        Double unshared = 0.0;
        prop1 = prop1.toLowerCase();
        prop2 = prop2.toLowerCase();

        for(int i = 0; i <= prop1.length() - n; i++){
            String substring1 = prop1.substring(i, i + n);
            unshared += prop2.indexOf(substring1) != -1 ? 0 : 1;
        }

        for (int i = 0; i <= prop2.length() - n; i++) {
            String substring2 = prop2.substring(i, i + n);
            if(prop1.indexOf(substring2) != -1){
                common += 1;
            }
            else {
                unshared += 1;
            }
        }
        Double sim = unshared / common;
        return sim;
    }
}
