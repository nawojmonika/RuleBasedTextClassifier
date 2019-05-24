package Sztuczna;

import java.io.*;
import java.util.*;

import Sztuczna.Algorithms.*;
import Sztuczna.Loaders.ArticleLoader;
import Sztuczna.Loaders.Loader;
import Sztuczna.Loaders.LoaderFactory;
import Sztuczna.Metrics.MetricFactory;
import Sztuczna.Metrics.SimilarityFactory;
import Sztuczna.Properties.PropertiesManager;

public class Runner {
    public double run(int k, String[] properties, String metric, String similarity, String[] labelsToWorkOn, String labelVal, String loader, boolean readFromCache) {
        File selectedFolder = new File("assets");


        Loader l = LoaderFactory.buildLoader(loader);
        ArrayList<Item> items = new ArrayList<>();
        for (final File fileEntry : selectedFolder.listFiles()) {
            if (fileEntry.isFile()) {
                if (fileEntry.getName().contains(l.getBaseFileName())) {
                    items.addAll(l.loadFile(fileEntry));
                }
            }
        }
        OutputWriter.addText("" + items.size());
        List<Item> testingArticles = items.subList(0, (int)(items.size() * 0.7));
        List<Item> learingArticles = items.subList(testingArticles.size(), items.size());
        OutputWriter.addText("" + testingArticles.size());
        OutputWriter.addText("" + learingArticles.size());

        PropertiesManager learingPropertiesManager = new PropertiesManager(learingArticles, readFromCache);
        for (String prop : properties) {
            learingPropertiesManager.addProperty(prop);
        }
        PropertiesManager testingPropertiesManager = new PropertiesManager(testingArticles, readFromCache);
        for (String prop : properties) {
            testingPropertiesManager.addProperty(prop);
        }

        learingPropertiesManager.normalize();
        testingPropertiesManager.normalize();

        KNN knn = new KNN(learingPropertiesManager, testingPropertiesManager, k, labelsToWorkOn, labelVal);
        return knn.perform(MetricFactory.build(metric), SimilarityFactory.build(similarity));
    }
}
