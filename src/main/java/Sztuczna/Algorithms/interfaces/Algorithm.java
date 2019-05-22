package Sztuczna.Algorithms.interfaces;

import Sztuczna.Article;

public interface Algorithm<T> {
    T perform(Article a);
}
