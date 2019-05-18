package Sztuczna.Metrics;

import Sztuczna.Properties.Property;

import java.util.*;

public class ChebyshevMetric extends HandleDifference implements Metric {
    public Double calculateDistance(ArrayList<Property> prop1, ArrayList<Property> prop2, TextSimilarityMetric metric) {
        ArrayList<Double> distances = new ArrayList<Double>();
        Iterator<Property> p1Iterator = prop1.iterator();
        Iterator<Property> p2Iterator = prop2.iterator();

        while (p1Iterator.hasNext() && p2Iterator.hasNext()) {
            Property singleProp1 = p1Iterator.next();
            Property singleProp2 = p2Iterator.next();
            Double diff = this.getDifference(singleProp1, singleProp2, metric);
            distances.add(Math.abs(diff));
        }

        return Collections.max(distances);
    }
}
