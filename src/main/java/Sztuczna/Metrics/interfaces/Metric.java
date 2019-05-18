package Sztuczna.Metrics.interfaces;

import Sztuczna.Properties.Property;

import java.util.ArrayList;

public interface Metric {
    public Double calculateDistance(ArrayList<Property> set1, ArrayList<Property> set2, TextSimilarityMetric metric);
}
