package Sztuczna.Algorithms;

import Sztuczna.Item;
import Sztuczna.OutputWriter;
import Sztuczna.Properties.PropertiesManager;
import Sztuczna.Properties.Property;
import Sztuczna.Metrics.interfaces.Metric;
import Sztuczna.Metrics.interfaces.TextSimilarityMetric;
import javafx.util.Pair;

import java.util.*;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

public class KNN {
    Integer K = 1;
    PropertiesManager learingPropertiesManger;
    PropertiesManager testingPropertiesManager;
    String[] labelsToWorkOn;
    String labelVal;

    ArrayList<ClassifiedArticle> classifiedArticles = new ArrayList<>();

    public KNN(PropertiesManager learingPropertiesManager, PropertiesManager testingPropertiesManager, Integer K, String[] labelsToWorkOn, String labelVal) {
        this.learingPropertiesManger = learingPropertiesManager;
        this.testingPropertiesManager = testingPropertiesManager;
        this.labelsToWorkOn = labelsToWorkOn;
        this.labelVal = labelVal;
        this.K = K;
    }

    public double perform(Metric metric, TextSimilarityMetric similarityMetric) {
        List<String> labels = Arrays.asList(this.labelsToWorkOn);
        List<Item> testingArticles = this.testingPropertiesManager.getArticles();
        List<Item> learingArticles = this.learingPropertiesManger.getArticlesForLabels(
                labels, labelVal, 500);

        Map<UUID, ArrayList<Property>> testingProperties = this.testingPropertiesManager.getUserProperties();
        Map<UUID, ArrayList<Property>> learingProperties = this.learingPropertiesManger.getUserProperties();
        for (Item testingArticle : testingArticles) {
            ArrayList<Property> singleTestingProperty = testingProperties.get(testingArticle.getUniqueId());
            ArrayList<Pair<String, Double>> distances = new ArrayList<>();

            learingArticles.stream().forEach(learningArticle -> {
                ArrayList<Property> propertToDistance = learingProperties.get(learningArticle.getUniqueId());
                distances.add(new Pair<>(learningArticle.getLabelByValue(labelVal), metric.calculateDistance(singleTestingProperty, propertToDistance, similarityMetric)));
            });

            Map<String, Integer> countedDistances = new HashMap<>();

            distances.stream()
                    .sorted(comparing(Pair::getValue))
                    .limit(this.K)
                    .forEach(stringDoublePair -> {
                        if (countedDistances.containsKey(stringDoublePair.getKey())) {
                            Integer toIncrement = countedDistances.get(stringDoublePair.getKey());
                            toIncrement++;
                            countedDistances.put(stringDoublePair.getKey(), toIncrement);
                        } else {
                            countedDistances.put(stringDoublePair.getKey(), 1);
                        }

                    });

            final String classifiedCountryId = countedDistances
                    .entrySet()
                    .stream()
                    .max(Comparator.comparing(Map.Entry::getValue))
                    .get()
                    .getKey();
            this.classifiedArticles.add(new ClassifiedArticle(testingArticle, classifiedCountryId, labelVal));
        }

        int numberOfArticlesToClassify = 0;
        for (String label : labels) {
            int numOfArticlesForLabel = 0;
            int numOfGoodArticles = 0;
            int numOfBadArticles = 0;
            for (ClassifiedArticle ca : this.classifiedArticles) {
                if (ca.article.getLabelByValue(labelVal).contains(label)) {
                    numOfArticlesForLabel++;
                    if (ca.wasClassifiedProperly()) {
                        numOfGoodArticles++;
                    } else {
                        numOfBadArticles++;
                    }
                }
            }
            numberOfArticlesToClassify += numOfArticlesForLabel;
            OutputWriter.addText(""+ numOfArticlesForLabel);
            OutputWriter.addText(""+ numOfGoodArticles);
        }


        long TP = this.classifiedArticles.stream().filter(ClassifiedArticle::wasClassifiedProperly).count();
        OutputWriter.addText(""+ (double)((double)TP / (double)numberOfArticlesToClassify));
        return (double)((double)TP / (double)testingArticles.size());
    }



    public void setK(Integer k) {
        K = k;
    }

    public Integer getK() {
        return this.K;
    }
}
