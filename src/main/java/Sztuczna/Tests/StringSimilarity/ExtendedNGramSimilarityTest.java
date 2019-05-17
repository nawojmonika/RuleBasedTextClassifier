package Sztuczna.Tests.StringSimilarity;

import Sztuczna.Metrics.ExtendedNGramSimilarity;
import org.junit.Assert;
import org.junit.Test;

public class ExtendedNGramSimilarityTest {
    @Test
    public void calculateSimilarity(){
        String prop1 = "PROGRAMMER";
        String prop2 = "Programming";
        ExtendedNGramSimilarity sim1 = new ExtendedNGramSimilarity();
        Double similarity1 = sim1.calculateSimilarity(prop1, prop2);
        Double expected1 = 0.5606060606060606;
        Assert.assertEquals(expected1, similarity1);


        String prop3 = "simple";
        String prop4 = "simplicity";
        ExtendedNGramSimilarity sim = new ExtendedNGramSimilarity();
        Double similarity2 = sim.calculateSimilarity(prop3, prop4);
        Double expected2 = 0.2727272727272727;
        Assert.assertEquals(expected2, similarity2);
    }
}
