package Sztuczna.Algorithms;

import Sztuczna.Article;

public class ClassifiedArticle {
    Article article;
    String className;

    public ClassifiedArticle(Article a, String className) {
        this.article = a;
        this.className = className;
    }

    public String getClassName() {
        return this.className;
    }

    public boolean wasClassifiedProperly() {
        return this.className.contains(this.article.getCountryLabel()) || this.article.getCountryLabel().contains(this.className);
    }

    public String toString() {
        return "ID: " + this.article.getOldId() + " in article:" + this.article.getCountryLabel() + " predicted: " + this.className;
    }
}
