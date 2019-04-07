package Sztuczna.Algorithms;

import java.util.ArrayList;
import java.util.HashSet;

public class PropertiesFactory {
    public static Property buildProperty(String propertyName, PropertiesManager propertiesManager) {
        if (propertyName == "DictionaryWordsInArticle") {
            return new DictionaryWordsInArticle(propertiesManager);
        } else if (propertyName == "NumberOfWordsInArticle") {
            return new NumberOfWordsInArticle(propertiesManager);
        } else if (propertyName == "FrequencyOfDictionaryWords") {
            return new FrequencyOfDictionaryWords(propertiesManager);
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
