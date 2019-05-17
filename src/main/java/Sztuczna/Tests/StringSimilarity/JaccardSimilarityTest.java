package Sztuczna.Tests.StringSimilarity;

import Sztuczna.Metrics.JaccardSimilarity;
import org.junit.Assert;
import org.junit.Test;

public class JaccardSimilarityTest {
    @Test
    public void calculateSimilarity(){
        String prop1 = "ofiara";
        String prop2 = "OFIARNY";
        JaccardSimilarity sim = new JaccardSimilarity();
        Double similarity = sim.calculateSimilarity(prop1, prop2);
        Double expected = 0.75;
        Assert.assertEquals(expected, similarity);
    }
}
