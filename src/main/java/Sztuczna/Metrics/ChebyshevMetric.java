package Sztuczna.Metrics;

import Sztuczna.Algorithms.Property;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

public class ChebyshevMetric implements Metric {
    public Double calculateDistance(Set<Property> set1, Set<Property> set2) {
        ArrayList<Double> distances = new ArrayList<Double>();
        Iterator<Property> p1Iterator = set1.iterator();
        Iterator<Property> p2Iterator = set2.iterator();


        while (p1Iterator.hasNext() && p2Iterator.hasNext()) {
            Double x = p1Iterator.next().getValue().doubleValue();
            Double y = p2Iterator.next().getValue().doubleValue();
            distances.add(Math.abs(x- y));
        }

        return Collections.max(distances);
    }
}
