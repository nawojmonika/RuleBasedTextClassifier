package Sztuczna.Properties;

import Sztuczna.Algorithms.interfaces.Algorithm;

public abstract class Property<T> implements Algorithm<T> {
    String name;
    String customLabel = null;
    T value;

    public Property(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public void setCustomLabel(String label) {
        this.customLabel = label;
    }

    public String toString() {
        String name = "PropertyName: " + this.name;
        if (this.customLabel != null) {
            name += " Custom label: " + this.customLabel;
        }
        name += " value: " + this.value;
        return name;
    }
}
