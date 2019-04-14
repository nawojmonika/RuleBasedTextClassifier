package Sztuczna.Metrics;

import Sztuczna.Algorithms.Property;

import java.util.Iterator;
import java.util.Set;

public class ManhattanMetric extends HandleDifference implements Metric{
    public Double calculateDistance(Set<Property> set1, Set<Property> set2) {
        Double dist = 0.0;

        Iterator<Property> p1Iterator = set1.iterator();
        Iterator<Property> p2Iterator = set2.iterator();


        while (p1Iterator.hasNext() && p2Iterator.hasNext()) {
            Double x = p1Iterator.next().getValue().doubleValue();
            Double y = p2Iterator.next().getValue().doubleValue();
            dist += Math.abs(x - y);
        }
        return dist;
    }
}
