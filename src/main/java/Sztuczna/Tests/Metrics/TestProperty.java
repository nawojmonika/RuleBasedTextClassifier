package Sztuczna.Tests.Metrics;

import Sztuczna.Algorithms.Properties.Property;
import Sztuczna.Article;

public class TestProperty extends Property<Integer> {
    public TestProperty(int value) {
        super("TestProperty", value);
    }
    @Override
    public Integer perform(Article a) {
        return null;
    }
}
