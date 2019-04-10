package Sztuczna.Algorithms;

import java.util.Set;

public interface Metric {
  Double calculateDistance(Set<Property> set1, Set<Property> set2);
}
