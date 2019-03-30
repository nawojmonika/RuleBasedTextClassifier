package Sztuczna.Algorithms;

import java.util.Properties;

public class PropertiesFactory {
    public static Property buildProperty(String propertyName, PropertiesManager propertiesManager) {
        if (propertyName == "NumberOfWords") {
            return new NumberOfWords(propertiesManager);
        }
        return new NumberOfWords(propertiesManager);
    }
}
