package Sztuczna;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import Sztuczna.Algorithms.*;
import Sztuczna.Metrics.ChebyshevMetric;
import Sztuczna.Metrics.ExtendedNGramSimilarity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

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

        PropertiesManager propertiesManager = new PropertiesManager(articles);
        propertiesManager.addProperty("DictionaryWordsInArticle");
        propertiesManager.addProperty("NumberOfWordsInArticle");
        propertiesManager.addProperty("FrequencyOfDictionaryWords");

        propertiesManager.addPropertyWithArguments("SelectedWordFromBeginingOfText", "SelecteUsaWordFromBeginningOfText", new ArrayList<String>(Arrays.asList("usa")));
        propertiesManager.addPropertyWithArguments("NumberOfWordsDefinedByUser", "AllPeople", new ArrayList<>(loadAllWordsFromFile("all-people-strings.lc.txt")));
        propertiesManager.addPropertyWithArguments("NumberOfWordsDefinedByUser", "AllPlaces", new ArrayList<>(loadAllWordsFromFile("all-places-strings.lc.txt")));
        propertiesManager.addPropertyWithArguments("NumberOfWordsDefinedByUser", "AllTopics", new ArrayList<>(loadAllWordsFromFile("all-topics-strings.lc.txt")));
        propertiesManager.addPropertyWithArguments("NumberOfWordsDefinedByUser", "AllOrgs", new ArrayList<>(loadAllWordsFromFile("all-orgs-strings.lc.txt")));
        propertiesManager.addPropertyWithArguments("NumberOfWordsDefinedByUser", "AllExchange", new ArrayList<>(loadAllWordsFromFile("all-exchanges-strings.lc.txt")));

        int defaultK = 3;

        KNN knn = new KNN(propertiesManager.getArticles(), propertiesManager.getUserProperties(), defaultK);
        System.out.println(knn.perform(new ChebyshevMetric(), new ExtendedNGramSimilarity()));
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
