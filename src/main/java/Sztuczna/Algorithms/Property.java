package Sztuczna.Algorithms;

public abstract class Property<T> implements Algorithm<Integer> {
    String name;
    T value;

    Property(String name, T value) {
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

    public String toString() {
        return "PropertyName: " + this.name + " value: " + this.value;
    }
}
