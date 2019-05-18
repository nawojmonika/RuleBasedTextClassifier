package Sztuczna;

import java.io.*;
import java.util.*;

import Sztuczna.Algorithms.*;
import Sztuczna.Properties.PropertiesManager;

public class Runner {
    public double run(int k, String[] properties, String metric, String similarity) {
        File selectedFolder = new File("assets");

        Loader l = new Loader();
        ArrayList<Article> articles = new ArrayList<>();
        for (final File fileEntry : selectedFolder.listFiles()) {
            if (fileEntry.isFile()) {
                if (fileEntry.getName().contains("reut2")) {
                    articles.addAll(l.loadFile(fileEntry));
                }
            }
        }
        System.out.println("Num of articles: " + articles.size());
        List<Article> testingArticles = articles.subList(0, (int)(articles.size() * 0.7));
        List<Article> learingArticles = articles.subList(testingArticles.size(), articles.size());
        System.out.println("Num of testting set: " + testingArticles.size());
        System.out.println("Num of learing set: " + learingArticles.size());

        PropertiesManager learingPropertiesManager = new PropertiesManager(learingArticles, true);
        for (String prop : properties) {
            learingPropertiesManager.addProperty(prop);
        }
        PropertiesManager testingPropertiesManager = new PropertiesManager(testingArticles, true);
        for (String prop : properties) {
            testingPropertiesManager.addProperty(prop);
        }

        learingPropertiesManager.normalize();
        testingPropertiesManager.normalize();

        KNN knn = new KNN(learingPropertiesManager, testingPropertiesManager, k);
        return knn.perform(MetricFactory.build(metric), SimilarityFactory.build(metric));
    }
}
