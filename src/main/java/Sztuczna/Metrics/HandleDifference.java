package Sztuczna.Metrics;

import Sztuczna.Algorithms.Property;

import java.util.Set;

public class HandleDifference {
  public Double getDifference(Property prop1, Property prop2, TextSimilarityMetric metric){
    Object val1 = prop1.getValue();
    Object val2 = prop2.getValue();
    if(val1 instanceof String && val2 instanceof String){
      return metric.calculateSimilarity((String) val1, (String) val2);
    }
    if (val1 instanceof Integer && val2 instanceof Integer) {
        return ((Integer) val1).doubleValue() - ((Integer) val2).doubleValue();
    }
    return (Double) val1 - (Double) val2;
  }
}

