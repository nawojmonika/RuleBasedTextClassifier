package Sztuczna.Metrics;

import Sztuczna.Metrics.interfaces.Metric;
import Sztuczna.Metrics.interfaces.TextSimilarityMetric;
import Sztuczna.Properties.Property;

import java.util.ArrayList;
import java.util.Iterator;

public class ManhattanMetric extends HandleDifference implements Metric {
    public Double calculateDistance(ArrayList<Property> set1, ArrayList<Property> set2, TextSimilarityMetric metric) {
        Double dist = 0.0;

        Iterator<Property> p1Iterator = set1.iterator();
        Iterator<Property> p2Iterator = set2.iterator();


        while (p1Iterator.hasNext() && p2Iterator.hasNext()) {
            Property prop1 = p1Iterator.next();
            Property prop2 = p2Iterator.next();
            Double diff = this.getDifference(prop1, prop2, metric);
            dist += Math.abs(diff);
        }
        return dist;
    }
}
