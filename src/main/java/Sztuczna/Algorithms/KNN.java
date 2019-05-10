package Sztuczna.Algorithms;

import Sztuczna.Article;
import Sztuczna.Metrics.HandleDifference;
import Sztuczna.Metrics.Metric;
import Sztuczna.Metrics.TextSimilarityMetric;
import javafx.util.Pair;

import java.util.*;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

public class KNN {
    Integer K = 1;
    PropertiesManager learingPropertiesManger;
    PropertiesManager testingPropertiesManager;

    ArrayList<ClassifiedArticle> classifiedArticles = new ArrayList<>();

    public KNN(PropertiesManager learingPropertiesManager, PropertiesManager testingPropertiesManager, Integer K) {
        this.learingPropertiesManger = learingPropertiesManager;
        this.testingPropertiesManager = testingPropertiesManager;
        this.K = K;
    }

    public double perform(Metric metric, TextSimilarityMetric similarityMetric) {
        List<Article> testingArticles = this.testingPropertiesManager.getArticles();
        List<Article> learingArticles = this.learingPropertiesManger.getArticlesForLabels(
                Arrays.asList(new String[] {"usa", "france", "germany", "canada"}), 10);
        int i = 0;

        Map<UUID, ArrayList<Property>> testingProperties = this.testingPropertiesManager.getUserProperties();
        Map<UUID, ArrayList<Property>> learingProperties = this.learingPropertiesManger.getUserProperties();
        for (Article testingArticle : testingArticles) {
            ArrayList<Property> singleTestingProperty = testingProperties.get(testingArticle.getUniqueId());
            ArrayList<Pair<String, Double>> distances = new ArrayList<>();

            learingArticles.stream().forEach(learningArticle -> {
                ArrayList<Property> propertToDistance = learingProperties.get(learningArticle.getUniqueId());
                distances.add(new Pair<>(learningArticle.getCountryLabel(), metric.calculateDistance(singleTestingProperty, propertToDistance, similarityMetric)));
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
            this.classifiedArticles.add(new ClassifiedArticle(testingArticle, classifiedCountryId));
            i++;
            System.out.println(i);
        }

        long TP = this.classifiedArticles.stream().filter(ClassifiedArticle::wasClassifiedProperly).count();
        return (double)((double)TP / (double)testingArticles.size());
    }



    public void setK(Integer k) {
        K = k;
    }

    public Integer getK() {
        return this.K;
    }
}
