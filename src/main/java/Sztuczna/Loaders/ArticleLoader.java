package Sztuczna.Loaders;

import Sztuczna.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ArticleLoader implements Loader {
    private Document nodeParser;
    public ArrayList<Item> loadFile(File file) {
        try {
            this.nodeParser = Jsoup.parse(file, "ISO-8859-1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements reuters = this.nodeParser.getElementsByTag("REUTERS");
        ArrayList<Item> articles = new ArrayList<>();
        for (Element reute : reuters) {
            articles.add(new Article(
                    getTitle(reute),
                    getBody(reute),
                    getId(reute),
                    getCountryCodes(reute),
                    getTopicsCodes(reute)
            ));
        }
        return articles;
    }

    @Override
    public String getBaseFileName() {
        return "reut2";
    }

    public String getId(Element reute) {
        String oldId = reute.attributes().get("oldid");
        return oldId;
    }

    public String getBody(Element reute) {
        Element text = reute.getElementsByTag("TEXT").get(0);
        Document possibleBodies = Jsoup.parseBodyFragment(text.html());
        return possibleBodies.text();
    }

    public String getTitle(Element reute) {
        Element text = reute.getElementsByTag("TEXT").get(0);
        Elements possibleTitles = text.getElementsByTag("TITLE");
        String title = "";
        if (possibleTitles.size() > 0) {
            title = possibleTitles.get(0).text();
        }
        return title;
    }

    public String getCountryCodes(Element reute) {
        Elements places = reute.getElementsByTag("PLACES");
        Elements countryCodes = Jsoup.parse(places.html()).getElementsByTag("D");
        ArrayList<String> codes = new ArrayList<>();
        for (Element code : countryCodes) {
            codes.add(code.text());
        }

        return String.join(",", codes);
    }

    public String getTopicsCodes(Element reute) {
        Elements places = reute.getElementsByTag("TOPICS");
        Elements countryCodes = Jsoup.parse(places.html()).getElementsByTag("D");
        ArrayList<String> codes = new ArrayList<>();
        for (Element code : countryCodes) {
            codes.add(code.text());
        }

        return String.join(",", codes);
    }
}
