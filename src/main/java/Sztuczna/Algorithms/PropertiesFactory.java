package Sztuczna.Algorithms;

public class PropertiesFactory {
    public static Property buildProperty(String propertyName, PropertiesManager propertiesManager) {
        if (propertyName == "NumOfWordsInArticleAndDictionary") {
            return new NumOfWordsInArticleAndDictionary(propertiesManager);
        } else if (propertyName == "NumberOfWordsInArticle") {
            return new NumberOfWordsInArticle(propertiesManager);
        } else if (propertyName == "NumOfWordsDefinedByUser") {
            return new NumOfWordsDefinedByUser(propertiesManager);
        } else if (propertyName == "FrequencyOfDictionaryWords") {
            return new FrequencyOfDictionaryWords(propertiesManager);
        } else if (propertyName == "SelectedWordFromBeginingOfText") {
            SelectedWordFromBeginingOfText s = new SelectedWordFromBeginingOfText(propertiesManager);
            // This is just a mock!
            // When GUI will be provided this will be changed to real word select
            s.setSelectedText("USA");
            return s;
        }
        return new NumOfWordsInArticleAndDictionary(propertiesManager);
    }
}
