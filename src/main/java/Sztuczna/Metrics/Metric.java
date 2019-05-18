package Sztuczna.Metrics;

import Sztuczna.Algorithms.Properties.Property;

import java.util.ArrayList;

public interface Metric {
    public Double calculateDistance(ArrayList<Property> set1, ArrayList<Property> set2, TextSimilarityMetric metric);
}
