package Sztuczna.Tests.Metrics;

import Sztuczna.Properties.Property;
import Sztuczna.Metrics.ChebyshevMetric;
import Sztuczna.Metrics.NGramSimilarity;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ChebyshevMetricTest {

    @Test
    public void calculateDistance() {
        TestProperty prop1 = new TestProperty(10);
        TestProperty prop2 = new TestProperty(2);
        TestProperty prop3 = new TestProperty(4);
        TestProperty prop4 = new TestProperty(0);
        TestProperty prop5 = new TestProperty(7);

        ArrayList<Property> x = new ArrayList();
        x.add(prop1);
        x.add(prop2);
        x.add(prop3);
        x.add(prop4);
        x.add(prop5);

        ArrayList<Property> y = new ArrayList();
        y.add(prop5);
        y.add(prop4);
        y.add(prop3);
        y.add(prop2);
        y.add(prop1);

        ChebyshevMetric metric = new ChebyshevMetric();
        NGramSimilarity sim = new NGramSimilarity();
        Double distance = metric.calculateDistance(x, y, sim);
        Double expected = 3.00;
        Assert.assertEquals(expected, distance);
    }
}