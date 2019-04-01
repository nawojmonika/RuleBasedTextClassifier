package Sztuczna.Algorithms;

import Sztuczna.Article;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KNN {
    ArrayList<Article> articles;
    Map<String, Set<Property>> userProperties;
    Integer K = 1;

    public KNN(ArrayList<Article> articles, Map<String, Set<Property>> userProperties, Integer K) {
        this.articles = articles;
        this.userProperties = userProperties;
        this.K = K;
    }

    public Double perform() {
        Integer tp = 0;

        List<Article> testingArticles = this.articles.subList(0, (int)(this.articles.size() * 0.7));
        List<Article> learingArticles = this.articles.subList(0, (int)(this.articles.size() * 0.3));

        for (Article testingArticle : this.articles) {
            Set<Property> singleTestingProperty = this.userProperties.get(testingArticle.getOldId());
            for (Article learingArticle : learingArticles) {
                Set<Property> singleLearingProperty = this.userProperties.get(learingArticle.getOldId());


            }
        }
    }

    public void setK(Integer k) {
        K = k;
    }

    public Integer getK() {
        return this.K;
    }
}
