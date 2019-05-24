package Sztuczna.Loaders;

public class LoaderFactory {
    public static Loader buildLoader(String loader) {
        if (loader.compareTo("bbc") == 0) {
            return new BBCLoader();
        } else if (loader.compareTo("article") == 0) {
            return new ArticleLoader();
        }
        throw new Error("There is no such loader as " + loader);
    }
}
