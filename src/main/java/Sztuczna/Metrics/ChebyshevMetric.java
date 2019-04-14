package Sztuczna.Metrics;

import Sztuczna.Algorithms.Property;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

public class ChebyshevMetric extends HandleDifference implements Metric {
    public Double calculateDistance(Set<Property> set1, Set<Property> set2, TextSimilarityMetric metric) {
        ArrayList<Double> distances = new ArrayList<Double>();
        Iterator<Property> p1Iterator = set1.iterator();
        Iterator<Property> p2Iterator = set2.iterator();


        while (p1Iterator.hasNext() && p2Iterator.hasNext()) {
            Property prop1 = p1Iterator.next();
            Property prop2 = p2Iterator.next();
            Double diff = this.getDifference(prop1, prop2, metric);
            distances.add(Math.abs(diff));
        }

        return Collections.max(distances);
    }
}
