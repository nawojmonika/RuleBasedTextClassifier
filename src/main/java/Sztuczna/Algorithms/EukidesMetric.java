package Sztuczna.Algorithms;

import java.util.Iterator;
import java.util.Set;

public class EukidesMetric implements Metric {
    public Double calculateDistance(Set<Property> set1, Set<Property> set2) {
        Double sum = 0.0;

        Iterator<Property> p1Iterator = set1.iterator();
        Iterator<Property> p2Iterator = set2.iterator();


        while (p1Iterator.hasNext() && p2Iterator.hasNext()) {
            sum = sum + Math.pow(p1Iterator.next().getValue().doubleValue() - p2Iterator.next().getValue().doubleValue(), 2.0);
        }
        return sum;
    }
}
