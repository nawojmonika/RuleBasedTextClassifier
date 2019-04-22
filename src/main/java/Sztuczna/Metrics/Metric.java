package Sztuczna.Metrics;

import Sztuczna.Algorithms.Property;

import java.util.ArrayList;
import java.util.Set;

public interface Metric {
    public Double calculateDistance(ArrayList<Property> set1, ArrayList<Property> set2, TextSimilarityMetric metric);
}
