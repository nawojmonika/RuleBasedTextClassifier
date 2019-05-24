package Sztuczna.Loaders;

import Sztuczna.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface Loader {
    ArrayList<Item> loadFile(File file);
    String getBaseFileName();
}
