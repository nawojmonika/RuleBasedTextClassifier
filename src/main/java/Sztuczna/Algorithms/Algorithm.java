package Sztuczna.Algorithms;

import Sztuczna.Article;

public interface Algorithm<T> {
    T perform(Article a);
}
