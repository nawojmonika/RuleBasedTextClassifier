package Sztuczna.Algorithms;

import Sztuczna.Article;

public class ClassifiedArticle {
    Article article;
    String className;
    String labelVal;

    public ClassifiedArticle(Article a, String className, String labelVal) {
        this.article = a;
        this.className = className;
        this.labelVal = labelVal;
    }

    public String getClassName() {
        return this.className;
    }

    public boolean wasClassifiedProperly() {
        return this.article.getLabelByValue(this.labelVal).contains(this.className);
    }

    public String toString() {
        return "ID: " + this.article.getOldId() + " in article:" + this.article.getCountryLabel() + " predicted: " + this.className;
    }
}
