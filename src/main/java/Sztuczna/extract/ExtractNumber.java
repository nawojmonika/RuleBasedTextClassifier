package Sztuczna.extract;

import javafx.util.Pair;

public class ExtractNumber {
    private Algorithm algorithm;
    ExtractNumber(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public Pair<String, Double> perform(String text) {
        return new Pair<String, Double>(this.algorithm.getName(), this.algorithm.perform(text));
    }
}
