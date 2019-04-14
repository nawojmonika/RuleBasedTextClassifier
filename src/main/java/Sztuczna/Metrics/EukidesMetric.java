package Sztuczna.Metrics;

import Sztuczna.Algorithms.Property;

import java.util.Iterator;
import java.util.Set;

public class EukidesMetric extends HandleDifference implements Metric {
    public Double calculateDistance(Set<Property> set1, Set<Property> set2, TextSimilarityMetric metric) {
        Double dist = 0.0;

        Iterator<Property> p1Iterator = set1.iterator();
        Iterator<Property> p2Iterator = set2.iterator();


        while (p1Iterator.hasNext() && p2Iterator.hasNext()) {
            Property prop1 = p1Iterator.next();
            Property prop2 = p2Iterator.next();
            Double diff = this.getDifference(prop1, prop2, metric);
            dist += Math.sqrt((Math.pow( diff, 2.0)));
        }
        return dist;
    }
}
