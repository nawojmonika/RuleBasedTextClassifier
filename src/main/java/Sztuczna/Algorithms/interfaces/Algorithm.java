package Sztuczna.Algorithms.interfaces;

import Sztuczna.Article;
import Sztuczna.Item;

public interface Algorithm<T> {
    T perform(Item a);
}
