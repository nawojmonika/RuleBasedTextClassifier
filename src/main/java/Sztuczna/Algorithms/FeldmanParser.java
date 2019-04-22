package Sztuczna.Algorithms;

import Sztuczna.Article;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FeldmanParser {
    ArrayList<String> parsedWordFactLines = null;
    Path fullPath = null;

    public FeldmanParser(Path fullPath) {
        this.fullPath = fullPath;
        this.parsedWordFactLines = getParsedWordFactLines();
    }

    private ArrayList<String> getParsedWordFactLines() {
        List<String> wordFactLines = null;
        try {
            wordFactLines = Files.readAllLines(this.fullPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> parsedWordFactLines = new ArrayList<>();

        String currentFact = "";
        boolean startedParsing = false;
        for (int lineIndex = 0 ; lineIndex < wordFactLines.size(); lineIndex++) {
            String currentSentenceToParse = wordFactLines.get(lineIndex);
            if (currentSentenceToParse.startsWith("bgfact")) {
                startedParsing = true;
                if (startedParsing) {
                    parsedWordFactLines.add(currentFact);
                    currentFact = "";
                    currentFact += currentSentenceToParse.replace("=", "");
                } else {
                    currentFact += currentSentenceToParse.replace("=", "");
                }
            } else if (startedParsing){
                currentFact += currentSentenceToParse.replace("=", "");
            }
        }
        return parsedWordFactLines;
    }

    public List<String> getParsedData(String countryName, FeldmanProperties property) {
        List<String> listForCountry = this.parsedWordFactLines
                .stream()
                .filter(s -> s.startsWith("bgfact(\""+countryName+"\",\"" + property.getPropertyName()))
                .collect(Collectors.toList());
        ArrayList<String> data = new ArrayList<>();
        Pattern findBraces = Pattern.compile("\\[(\\\".*\\\")*\\]");
        for (String countryData : listForCountry) {
            Matcher matcher = findBraces.matcher(countryData);
            while (matcher.find()) {
                if (matcher.group(1) != null){
                    data.addAll(Arrays.asList(matcher.group(1).replace("\"", "").split(",")));
                }
            }
        }
        return data;
    }

    public ArrayList<String> getAllParsedDataForCountry(String countryName) {
        ArrayList<String> wordsForCountry = new ArrayList<>();
        for(FeldmanProperties property : FeldmanProperties.values()) {
            wordsForCountry.addAll(this.getParsedData(countryName, property));
        }
        return wordsForCountry;
    }
}
