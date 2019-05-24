package Sztuczna;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import Sztuczna.Algorithms.*;
import Sztuczna.Properties.PropertiesManager;
import Sztuczna.Metrics.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;

public class FXMLController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        DirectoryChooser fileChooser = new DirectoryChooser();
        File selectedFolder = fileChooser.showDialog(null);

        Loader l = new Loader();
        ArrayList<Article> articles = new ArrayList<>();
        for (final File fileEntry : selectedFolder.listFiles()) {
            if (fileEntry.isFile()) {
                if (fileEntry.getName().contains("reut2")) {
                    articles.addAll(l.loadFile(fileEntry));
                }
                ;
            }
        }
        List<Article> testingArticles = articles.subList(0, (int)(articles.size() * 0.7));
        List<Article> learingArticles = articles.subList(testingArticles.size(), articles.size());
        System.out.println(learingArticles.size());

        PropertiesManager learingPropertiesManager = new PropertiesManager(learingArticles, true);
        learingPropertiesManager.addProperty("FirstDictionaryWordInArticle");
        learingPropertiesManager.addProperty("LastDictionaryWordInArticle");
        learingPropertiesManager.addProperty("NumberOfWordsInArticle");
        learingPropertiesManager.addProperty("DictionaryWordsInArticle");
        learingPropertiesManager.addProperty("NumberOfDictionaryWordsInFirstPartOfArticle");
        learingPropertiesManager.addProperty("NumberOfDictionaryWordsInLastPartOfArticle");
        learingPropertiesManager.addProperty("MostFrequentDictionaryWord");
        learingPropertiesManager.addProperty("LeastFrequentDictionaryWord");
        learingPropertiesManager.addPropertyWithArguments("NumberOfWordsDefinedByUser", "AllPeople", new ArrayList<>(loadAllWordsFromFile("all-people-strings.lc.txt")));
        PropertiesManager testingPropertiesManager = new PropertiesManager(testingArticles, true);
        testingPropertiesManager.addProperty("FirstDictionaryWordInArticle");
        testingPropertiesManager.addProperty("LastDictionaryWordInArticle");
        testingPropertiesManager.addProperty("NumberOfWordsInArticle");
        testingPropertiesManager.addProperty("DictionaryWordsInArticle");
        testingPropertiesManager.addProperty("NumberOfDictionaryWordsInFirstPartOfArticle");
        testingPropertiesManager.addProperty("NumberOfDictionaryWordsInLastPartOfArticle");
        testingPropertiesManager.addProperty("MostFrequentDictionaryWord");
        testingPropertiesManager.addProperty("LeastFrequentDictionaryWord");
        testingPropertiesManager.addPropertyWithArguments("NumberOfWordsDefinedByUser", "AllPeople", new ArrayList<>(loadAllWordsFromFile("all-people-strings.lc.txt")));
        System.out.println("Done");
        // propertiesManager.addProperty("NumberOfWordsInArticle");
        // propertiesManager.addProperty("FrequencyOfDictionaryWords");

        // propertiesManager.addProperty("CountryByNumOfWordsDefinedByUser");
        // propertiesManager.addPropertyWithArguments("SelectedWordFromBeginingOfText", "SelecteUsaWordFromBeginningOfText", new ArrayList<String>(Arrays.asList("usa")));
        // propertiesManager.addPropertyWithArguments("NumberOfWordsDefinedByUser", "AllPlaces", new ArrayList<>(loadAllWordsFromFile("all-places-strings.lc.txt")));
        // propertiesManager.addPropertyWithArguments("NumberOfWordsDefinedByUser", "AllTopics", new ArrayList<>(loadAllWordsFromFile("all-topics-strings.lc.txt")));
        // propertiesManager.addPropertyWithArguments("NumberOfWordsDefinedByUser", "AllOrgs", new ArrayList<>(loadAllWordsFromFile("all-orgs-strings.lc.txt")));
        // propertiesManager.addPropertyWithArguments("NumberOfWordsDefinedByUser", "AllExchange", new ArrayList<>(loadAllWordsFromFile("all-exchanges-strings.lc.txt")));
        // propertiesManager.addPropertyWithArguments("NumberOfWordsDefinedByUser", "GermanyKeyWordsNum", fp.getAllParsedDataForCountry("Germany"));
        // propertiesManager.addPropertyWithArguments("NumberOfWordsDefinedByUser", "UnitedStatesWordsNum", fp.getAllParsedDataForCountry("United States"));
        // propertiesManager.addPropertyWithArguments("NumberOfWordsDefinedByUser", "FranceWordsNum", fp.getAllParsedDataForCountry("France"));
        // propertiesManager.addPropertyWithArguments("NumberOfWordsDefinedByUser", "UnitedKingdomWordsNum", fp.getAllParsedDataForCountry("United Kingdom"));
        // propertiesManager.addPropertyWithArguments("NumberOfWordsDefinedByUser", "CanadaWordsNum", fp.getAllParsedDataForCountry("Canada"));
        // propertiesManager.addPropertyWithArguments("NumberOfWordsDefinedByUser", "JapanWordsNum", fp.getAllParsedDataForCountry("Japan"));

        int defaultK = 2;

        KNN knn = new KNN(learingPropertiesManager, testingPropertiesManager, defaultK, null, null);
        System.out.println(knn.perform(new EukidesMetric(), new SimpleStringCompare()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public List<String> loadAllWordsFromFile(String fileName) {
        Path p = Paths.get(System.getProperty("user.dir"),"assets", fileName);
        try {
            return Files.readAllLines(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ERROR");
        return new ArrayList<String>();
    }
}
