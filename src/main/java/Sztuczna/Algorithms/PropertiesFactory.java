package Sztuczna.Algorithms;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class PropertiesFactory {
    public static FeldmanParser fp = new FeldmanParser(Paths.get(System.getProperty("user.dir"),"assets", "feldman-cia-worldfactbook-data.txt"));
    public static Map<String, ArrayList<String>> wordsByCountry = new LinkedHashMap<String, ArrayList<String>>() {{
        put("Germany", fp.getAllParsedDataForCountry("Germany"));
        put("United States", fp.getAllParsedDataForCountry("United States"));
        put("France", fp.getAllParsedDataForCountry("France"));
        put("United Kingdom", fp.getAllParsedDataForCountry("United Kingdom"));
        put("Canada", fp.getAllParsedDataForCountry("Canada"));
        put("Japan", fp.getAllParsedDataForCountry("Japan"));
    }};

    public static Property buildProperty(String propertyName, PropertiesManager propertiesManager) {
        if (propertyName == "DictionaryWordsInArticle") {
            return new DictionaryWordsInArticle(propertiesManager);
        } else if (propertyName.compareTo("NumberOfWordsInArticle") == 0) {
            return new NumberOfWordsInArticle(propertiesManager);
        } else if (propertyName.compareTo("FrequencyOfDictionaryWords") == 0) {
            return new FrequencyOfDictionaryWords(propertiesManager);
        } else if (propertyName.compareTo("CountryByNumOfWordsDefinedByUser") == 0) {
            return new CountryByNumOfWordsDefinedByUser(propertiesManager, wordsByCountry);
        } else if (propertyName.compareTo("FirstDictionaryWordInArticle") == 0) {
            return new FirstDictionaryWordInArticle(propertiesManager);
        } else if (propertyName.compareTo("LastDictionaryWordInArticle") == 0) {
            return new LastDictionaryWordInArticle(propertiesManager);
        } else if (propertyName.compareTo("NumberOfDictionaryWordsInFirstPartOfArticle") == 0) {
            return new NumberOfDictionaryWordsInFirstPartOfArticle(propertiesManager);
        } else if (propertyName.compareTo("NumberOfDictionaryWordsInLastPartOfArticle") == 0) {
            return new NumberOfDictionaryWordsInLastPartOfArticle(propertiesManager);
        } else if (propertyName.compareTo("MostFrequentDictionaryWord") == 0) {
            return new MostFrequentDictionaryWord(propertiesManager);
        } else if (propertyName.compareTo("LeastFrequentDictionaryWord") == 0) {
            return new LeastFrequentDictionaryWord(propertiesManager);
        }
        return null;
    }
    public static Property buildPropertyWithArguments(String propertyName, ArrayList<String> arguments, PropertiesManager propertiesManager) {
        Property propertyWithoutArgumetns = buildProperty(propertyName, propertiesManager);
        if (propertyWithoutArgumetns == null) {
            if (propertyName.compareTo("NumberOfWordsDefinedByUser") == 0) {
                NumOfWordsDefinedByUser numOfWordsDefinedByUser = new NumOfWordsDefinedByUser(propertiesManager);
                numOfWordsDefinedByUser.setWordsDefinedByUser(new HashSet<>(arguments));
                return numOfWordsDefinedByUser;
            } else if (propertyName.compareTo("SelectedWordFromBeginingOfText") == 0) {
                SelectedWordFromBeginingOfText s = new SelectedWordFromBeginingOfText(propertiesManager);
                s.setSelectedText(arguments.get(0));
                return s;
            }
        } else {
            return propertyWithoutArgumetns;
        }
        return null;
    }
}
