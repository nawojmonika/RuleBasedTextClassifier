package Sztuczna.Algorithms;

import Sztuczna.Article;


import java.util.*;


import java.util.stream.Collectors;

public class PropertiesManager {
    Map<String, Set<Property>> userProperties;
    Map<String, Integer> wordsDictionary;
    ArrayList<Article> articles;

    public PropertiesManager(ArrayList<Article> articles) {
        for (Article article : articles) {
            article.performWordsAlgorithm(new TokenizeWords())
                    .performWordsAlgorithm(new ToLowerCase())
                    .performWordsAlgorithm(new StopListAlgorithm())
                    .performWordsAlgorithm(new Stemization());
        }
        this.articles = articles;
        fillTheWordProperties();
        fillUserPropertiesArticlesId();
    }

    public void fillTheWordProperties() {
        this.wordsDictionary = new HashMap<>();
        for (Article article : articles) {
            final ArrayList<String> wordsFromArticle = article.getAlgorithmsWords();
            for (String singleWord : wordsFromArticle) {
                if (wordsDictionary.containsKey(singleWord)) {
                    int numberOfElementsInDictionary = wordsDictionary.get(singleWord);
                    numberOfElementsInDictionary++;
                    wordsDictionary.put(singleWord, numberOfElementsInDictionary);
                } else {
                    wordsDictionary.put(singleWord, 1);
                }
            }
        }

        this.wordsDictionary = filterByWordsThreshold(this.wordsDictionary);
    }

    static Map<String, Integer> filterByWordsThreshold(Map<String, Integer> map) {
        int numberOfThresholfdForTooComomonWords = 30;
        return map.entrySet()
                .stream()
                .filter(entry -> entry.getValue() < numberOfThresholfdForTooComomonWords)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void fillUserPropertiesArticlesId() {
        this.userProperties = new HashMap<>();
        for (Article a : articles) {
            userProperties.put(a.getOldId(), new HashSet<>());
        }
    }

    public void addProperty(String property) {
        for (Article a : articles) {
            Property p = PropertiesFactory.buildProperty(property, this);
            Set<Property> propertiesHandler = userProperties.get(a.getOldId());
            propertiesHandler.add(p);
            p.perform(a);
            userProperties.put(a.getOldId(), propertiesHandler);
        }
    }

    public Map<String, Integer> getWordsDictionary() {
        return this.wordsDictionary;
    }

    public String propertiesToString() {
        StringBuilder articlesProperties = new StringBuilder();
        for (Map.Entry<String, Set<Property>> userProperties : userProperties.entrySet()) {
            articlesProperties.append("ID: " + userProperties.getKey());
            articlesProperties.append(System.lineSeparator());
            for (Property singleProperty : userProperties.getValue()) {
                articlesProperties.append("\t");
                articlesProperties.append(singleProperty.toString());
                articlesProperties.append(System.lineSeparator());
            }
            articlesProperties.append(System.lineSeparator());
        }
        return  articlesProperties.toString();
    }
}
