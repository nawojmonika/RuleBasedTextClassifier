package Sztuczna;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.ResourceBundle;

import Sztuczna.Algorithms.*;
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
                };
            }
        }

        PropertiesManager propertiesManager = new PropertiesManager(articles);
        propertiesManager.addProperty("NumOfWordsInArticleAndDictionary");
        propertiesManager.addProperty("NumberOfWordsInArticle");
        propertiesManager.addProperty("NumOfWordsDefinedByUser");
        propertiesManager.addProperty("SelectedWordFromBeginingOfText");
        propertiesManager.addProperty("FrequencyOfDictionaryWords");
        System.out.println(propertiesManager.propertiesToString());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
