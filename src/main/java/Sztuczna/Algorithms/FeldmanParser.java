package Sztuczna.Algorithms;

import Sztuczna.Article;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FeldmanParser {
    ArrayList<String> parsedWordFactLines = null;
    Path fullPath = null;

    public FeldmanParser(Path fullPath) {
        this.fullPath = fullPath;
    }

    public ArrayList<String> getParsedWOrdFactLines() {
        List<String> wordFactLines = null;
        try {
            wordFactLines = Files.readAllLines(this.fullPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> parsedWordFactLines = new ArrayList<>();

        String currentFact = "";
        for (int lineIndex = 0 ; lineIndex < wordFactLines.size(); lineIndex++) {
            String currentSentenceToParse = wordFactLines.get(lineIndex);
            if (currentSentenceToParse.startsWith("bgfact")) {
                if (!currentSentenceToParse.endsWith("=")) {
                    currentFact += currentSentenceToParse;
                    parsedWordFactLines.add(currentFact);
                    currentFact = "";
                } else {
                    String sentenceWithoutEqualSign = currentSentenceToParse.substring(0, currentSentenceToParse.length() - 1);
                    currentFact += sentenceWithoutEqualSign;
                }
            }
        }
        return parsedWordFactLines;
    }
}
