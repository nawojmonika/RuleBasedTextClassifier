package Sztuczna;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import Sztuczna.Algorithms.StopListAlgorithm;
import Sztuczna.Algorithms.ToLowerCase;
import Sztuczna.Algorithms.TokenizeWords;
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

        articles.get(1)
                .performWordsAlgorithm(new TokenizeWords())
                .performWordsAlgorithm(new ToLowerCase())
                .performWordsAlgorithm(new StopListAlgorithm());
        System.out.println(articles.get(1).getAlgorithmsWords());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
