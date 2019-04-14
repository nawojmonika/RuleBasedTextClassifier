package Sztuczna.Algorithms;

import Sztuczna.Article;
import Sztuczna.Metrics.HandleDifference;
import javafx.util.Pair;

import java.util.*;

public class KNN {
    ArrayList<Article> articles;
    Map<String, Set<Property>> userProperties;
    Integer K = 1;

    ArrayList<ClassifiedArticle> classifiedArticles = new ArrayList<>();

    public KNN(ArrayList<Article> articles, Map<String, Set<Property>> userProperties, Integer K) {
        this.articles = articles;
        this.userProperties = userProperties;
        this.K = K;
    }

    public double perform(HandleDifference metric) {
        List<Article> testingArticles = this.articles.subList(0, (int)(this.articles.size() * 0.7));
        List<Article> learingArticles = this.articles.subList(testingArticles.size(), this.articles.size());
        int i = 0;

        for (Article testingArticle : testingArticles) {
            Set<Property> singleTestingProperty = this.userProperties.get(testingArticle.getOldId());
            ArrayList<Pair<String, Double>> distances = new ArrayList<>();

            learingArticles.stream().forEach(article -> {
                Set<Property> propertToDistance = this.userProperties.get(article.getOldId());
                distances.add(new Pair<>(article.getCountryLabel(), metric.calculateDistance(singleTestingProperty, propertToDistance)));
            });

            Map<String, Integer> countedDistances = new HashMap<>();

            distances.stream()
                    .sorted(Comparator.comparing(Pair::getValue))
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
        // this.classifiedArticles.stream().forEach(classifiedArticle -> System.out.println(classifiedArticle.toString()));

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
