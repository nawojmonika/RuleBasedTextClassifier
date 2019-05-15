package Sztuczna.Algorithms;

import Sztuczna.Metrics.ChebyshevMetric;
import Sztuczna.Metrics.EukidesMetric;
import Sztuczna.Metrics.ManhattanMetric;
import Sztuczna.Metrics.Metric;


public class MetricFactory {
    public static Metric build(String metric) {
        if (metric.compareTo("ChebyshevMetric") == 0) {
            return new ChebyshevMetric();
        } else if (metric.compareTo("EukidesMetric") == 0) {
            return new EukidesMetric();
        } else if (metric.compareTo("ManhattanMetric") == 0) {
            return new ManhattanMetric();
        }
        return new EukidesMetric();
    }
}
