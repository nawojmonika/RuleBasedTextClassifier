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
    ArrayList<Article> articles;
    Map<UUID, ArrayList<Property>> userProperties;
    Integer K = 1;

    ArrayList<ClassifiedArticle> classifiedArticles = new ArrayList<>();

    public KNN(ArrayList<Article> articles, Map<UUID, ArrayList<Property>> userProperties, Integer K) {
        this.articles = articles;
        this.userProperties = userProperties;
        this.K = K;
    }

    public double perform(Metric metric, TextSimilarityMetric similarityMetric) {
        List<Article> testingArticles = this.articles.subList(0, (int)(this.articles.size() * 0.7));
        List<Article> learingArticles = this.articles.subList(testingArticles.size(), this.articles.size());
        int i = 0;

        for (Article testingArticle : testingArticles) {
            ArrayList<Property> singleTestingProperty = this.userProperties.get(testingArticle.getUniqueId());
            ArrayList<Pair<String, Double>> distances = new ArrayList<>();

            learingArticles.stream().forEach(learningArticle -> {
                ArrayList<Property> propertToDistance = this.userProperties.get(learningArticle.getUniqueId());
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
