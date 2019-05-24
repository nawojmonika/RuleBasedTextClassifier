package Sztuczna.Algorithms;

import Sztuczna.Item;

public class ClassifiedArticle {
    Item article;
    String className;
    String labelVal;

    public ClassifiedArticle(Item a, String className, String labelVal) {
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
}
