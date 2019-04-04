package Sztuczna.Algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PropertiesFactory {
    public static Property buildProperty(String propertyName, PropertiesManager propertiesManager) {
        if (propertyName == "NumOfWordsInArticleAndDictionary") {
            return new NumOfWordsInArticleAndDictionary(propertiesManager);
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
