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
        } else if (propertyName == "NumberOfWordsInArticle") {
            return new NumberOfWordsInArticle(propertiesManager);
        } else if (propertyName == "FrequencyOfDictionaryWords") {
            return new FrequencyOfDictionaryWords(propertiesManager);
        } else if (propertyName == "CountryByNumOfWordsDefinedByUser") {
            return new CountryByNumOfWordsDefinedByUser(propertiesManager, wordsByCountry);
        } else if (propertyName == "FirstDictionaryWordInArticle") {
            return new FirstDictionaryWordInArticle(propertiesManager);
        } else if (propertyName == "LastDictionaryWordInArticle") {
            return new LastDictionaryWordInArticle(propertiesManager);
        } else if (propertyName == "NumberOfDictionaryWordsInFirstPartOfArticle") {
            return new NumberOfDictionaryWordsInFirstPartOfArticle(propertiesManager);
        } else if (propertyName == "NumberOfDictionaryWordsInLastPartOfArticle") {
            return new NumberOfDictionaryWordsInLastPartOfArticle(propertiesManager);
        } else if (propertyName == "MostFrequentDictionaryWord") {
            return new MostFrequentDictionaryWord(propertiesManager);
        } else if (propertyName == "LeastFrequentDictionaryWord") {
            return new LeastFrequentDictionaryWord(propertiesManager);
        }
        return null;
    }
    public static Property buildPropertyWithArguments(String propertyName, ArrayList<String> arguments, PropertiesManager propertiesManager) {
        Property propertyWithoutArgumetns = buildProperty(propertyName, propertiesManager);
        if (propertyWithoutArgumetns == null) {
            if (propertyName == "NumberOfWordsDefinedByUser") {
                NumOfWordsDefinedByUser numOfWordsDefinedByUser = new NumOfWordsDefinedByUser(propertiesManager);
                numOfWordsDefinedByUser.setWordsDefinedByUser(new HashSet<>(arguments));
                return numOfWordsDefinedByUser;
            } else if (propertyName == "SelectedWordFromBeginingOfText") {
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
