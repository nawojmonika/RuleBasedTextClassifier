package Sztuczna;

public class Article {
    private String title;
    private String body;
    private String oldId;
    private String countryLabel;

    public Article(String title, String body, String oldId, String countryLabel) {
        this.title = title;
        this.body = body;
        this.oldId = oldId;
        this.countryLabel = countryLabel;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getOldId() {
        return oldId;
    }

    public String getCountryLabel() {
        return countryLabel;
    }
}
