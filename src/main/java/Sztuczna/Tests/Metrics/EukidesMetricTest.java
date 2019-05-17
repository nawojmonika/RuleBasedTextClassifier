package Sztuczna.Tests.Metrics;

import Sztuczna.Algorithms.Property;
import Sztuczna.Metrics.EukidesMetric;
import Sztuczna.Metrics.NGramSimilarity;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class EukidesMetricTest {

    @Test
    public void calculateDistance() {
        TestProperty prop1 = new TestProperty(5);
        TestProperty prop2 = new TestProperty(2);
        TestProperty prop3 = new TestProperty(9);

        ArrayList<Property> x = new ArrayList();
        x.add(prop1);
        x.add(prop3);

        ArrayList<Property> y = new ArrayList();
        y.add(prop2);
        y.add(prop1);

        EukidesMetric metric = new EukidesMetric();
        NGramSimilarity sim = new NGramSimilarity();
        Double distance = metric.calculateDistance(x, y, sim);
        Double expected = 5.00;
        Assert.assertEquals(expected, distance);
    }
}