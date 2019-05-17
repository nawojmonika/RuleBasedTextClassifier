package Sztuczna.Tests.StringSimilarity;

import Sztuczna.Metrics.NGramSimilarity;
import org.junit.Assert;
import org.junit.Test;

public class NGramSimilarityTest {
    @Test
    public void calculateSimilarity(){
        String prop1 = "ofiara";
        String prop2 = "OFIARNY";
        NGramSimilarity sim = new NGramSimilarity();
        Double similarity = sim.calculateSimilarity(prop1, prop2);
        Double expected = 0.6666666666666666;
        Assert.assertEquals(expected, similarity);
    }
}
