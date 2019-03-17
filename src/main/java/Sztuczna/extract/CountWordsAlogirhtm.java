package Sztuczna.extract;

public class CountWordsAlogirhtm implements Algorithm{
    @Override
    public double perform(String text) {
        return text.split("\\W+").length;
    }

    @Override
    public String getName() {
        return "CountedWords";
    }
}
