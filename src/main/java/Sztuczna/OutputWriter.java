package Sztuczna;

import java.util.StringJoiner;

public class OutputWriter {
    private static StringJoiner output = new StringJoiner(";");

    public static void addText(String text) {
        OutputWriter.output.add(text);
    }

    public static String getString() {
        return output.toString();
    }
}
