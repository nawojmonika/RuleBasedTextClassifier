package Sztuczna.Properties;

import Sztuczna.Algorithms.*;
import Sztuczna.Item;
import javafx.util.Pair;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PropertiesManager {
    Map<UUID, ArrayList<Property>> userProperties;
    Map<String, Double> wordsDictionary;
    List<Item> articles;

    public PropertiesManager(List<Item> articles, boolean readFromCache) {
        for (Item article : articles) {
            article.performWordsAlgorithm(new TokenizeWords())
                    .performWordsAlgorithm(new ToLowerCase())
                    .performWordsAlgorithm(new StopListAlgorithm())
                    .performWordsAlgorithm(new Stemization())
                    .performTermsAlgorithm(new TerminizeWords());
        }
        this.articles = articles;
        this.wordsDictionary = new HashMap<>();
        if (!readFromCache) {
            fillTheWordProperties();
        } else {
            this.readWordDictionary();
        }
        fillUserPropertiesArticlesId();
    }

    public List<Item> getArticlesForLabels(List<String> labels, String labelVal, int numberOfArticles) {
        Map<String, Integer> labelsForCountries = new HashMap<>();
        for (String label: labels) {
            labelsForCountries.put(label, 0);
        }
        List<Item> articlesForLabels = new ArrayList<>();
        for (Item article : this.articles) {
            String label = article.getLabelByValue(labelVal);
            if (labelsForCountries.containsKey(label) && labelsForCountries.get(label) <= numberOfArticles) {
                labelsForCountries.put(label, labelsForCountries.get(label) + 1);
                articlesForLabels.add(article);
            }
        }
        return articlesForLabels;
    }

    public void fillTheWordProperties() {
        // https://nlpforhackers.io/tf-idf/
        List<List<String>> docsAllWords = new ArrayList<>();
        for (Item article : articles) {
            docsAllWords.add(article.getAlgorithmsWords());
        }

        TFIDFCalculator calculator = new TFIDFCalculator();

        int articleNum = 0;
        int numOfArticles = articles.size();
        for (Item article : articles) {
            // System.out.println("Done: " + ++articleNum + " / " + numOfArticles);
            List<String> wordsInArticle = article.getAlgorithmsWords();
            Map<String, Double> wordsWithTFIDF = new HashMap<>();
            for (String word : wordsInArticle) {
                if (!this.wordsDictionary.containsKey(word)) {
                    // System.out.println(word + calculator.tfIdf(wordsInArticle, docsAllWords, word));
                    Double tfidf = calculator.tfIdf(wordsInArticle, docsAllWords, word);
                    if (tfidf > 0.3) {
                        this.wordsDictionary.put(word, tfidf);
                    }
                }
            }
        }
    }

    public void fillUserPropertiesArticlesId() {
        this.userProperties = new HashMap<>();
        for (Item a : articles) {
            userProperties.put(a.getUniqueId(), new ArrayList<>());
        }
    }

    public void addProperty(String property) {
        for (Item a : articles) {
            Property p = PropertiesFactory.buildProperty(property, this);
            ArrayList<Property> propertiesHandler = userProperties.get(a.getUniqueId());
            propertiesHandler.add(p);
            p.perform(a);
            userProperties.put(a.getUniqueId(), propertiesHandler);
        }
    }

    public void addPropertyWithArguments(String property, String customLabel, ArrayList<String> arguments) {
        for (Item a : articles) {
            Property p = PropertiesFactory.buildPropertyWithArguments(property, arguments, this);
            p.setCustomLabel(customLabel);
            ArrayList<Property> propertiesHandler = userProperties.get(a.getUniqueId());
            propertiesHandler.add(p);
            p.perform(a);
            userProperties.put(a.getUniqueId(), propertiesHandler);
        }
    }

    public Map<String, Double> getWordsDictionary() {
        return this.wordsDictionary;
    }

    public String propertiesToString() {
        StringBuilder articlesProperties = new StringBuilder();
        for (Map.Entry<UUID, ArrayList<Property>> userProperties : userProperties.entrySet()) {
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

    public Map<UUID, ArrayList<Property>> getUserProperties() {
        return userProperties;
    }

    public List<Item> getArticles() {
        return articles;
    }

    public void writeWordDictionary() {
        FileOutputStream valuesOutputStream = null;
        try {
            BufferedWriter keysWriter = new BufferedWriter(new FileWriter("dictionary_keys.txt"));
            valuesOutputStream = new FileOutputStream("dictionary_values.txt");
            DataOutputStream dos = new DataOutputStream(valuesOutputStream);
            this.wordsDictionary.entrySet().stream().forEach(stringDoubleEntry -> {
                try {
                    keysWriter.write(stringDoubleEntry.getKey());
                    keysWriter.write("\n");
                    dos.writeDouble(stringDoubleEntry.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            keysWriter.close();
            dos.flush();
            dos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readWordDictionary() {
        FileInputStream fos = null;
        try {
            fos = new FileInputStream("dictionary_values.txt");
            DataInputStream dos = new DataInputStream(fos);
            List<Double> values = new ArrayList<>();
            while (dos.available() > 0) {
                values.add(dos.readDouble());
            }

            List<String> lines = Files.readAllLines(Paths.get("dictionary_keys.txt"));
            for (int i = 0 ; i < lines.size() ; i++) {
                this.wordsDictionary.put(lines.get(i), values.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double normaliseEquation(double min, double max, double x){
        return (x - min) / (max - min);
    }

    public void normalize() {
        Map<String,Pair<Double, Double>> maxMinForProperties = new HashMap<>();

        for (ArrayList<Property> articleProperties : this.userProperties.values()) {
            for (Property property : articleProperties) {
                Object propValue = property.getValue();
                if (!(propValue instanceof String)) {
                    if (propValue instanceof Double) {
                        if (maxMinForProperties.containsKey(property.getName())) {
                            if (maxMinForProperties.get(property.getName()).getKey() < (Double) propValue) {
                                maxMinForProperties.put(property.getName(), new Pair((Double)propValue, maxMinForProperties.get(property.getName()).getValue()));
                            }
                            if (maxMinForProperties.get(property.getName()).getValue() > (Double) propValue) {
                                maxMinForProperties.put(property.getName(), new Pair(maxMinForProperties.get(property.getName()).getKey(), (Double)propValue));
                            }
                        } else {
                            maxMinForProperties.put(property.getName(), new Pair<>((Double) propValue, (Double) propValue));
                        }
                    }
                }
            }
        }

        for (ArrayList<Property> articleProperties : this.userProperties.values()) {
            for (Property property : articleProperties) {
                Object propValue = property.getValue();
                if (!(propValue instanceof String)) {
                    Double min = maxMinForProperties.get(property.getName()).getValue();
                    Double max = maxMinForProperties.get(property.getName()).getKey();
                    property.setValue(this.normaliseEquation(min, max, (Double)propValue));
                }
            }
        }
    }
}
