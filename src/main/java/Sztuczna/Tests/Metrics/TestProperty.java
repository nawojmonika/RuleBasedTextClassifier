package Sztuczna.Tests.Metrics;

import Sztuczna.Item;
import Sztuczna.Properties.Property;

public class TestProperty extends Property<Integer> {
    public TestProperty(int value) {
        super("TestProperty", value);
    }
    @Override
    public Integer perform(Item a) {
        return null;
    }
}
