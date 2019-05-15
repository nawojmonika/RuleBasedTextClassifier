package Sztuczna;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.cli.*;

import java.util.StringJoiner;


public class MainApp {
    public static void main(String[] args) {
        Options options = new Options();

        Option k = new Option("k", "k", true, "K - neighbours");
        k.setRequired(true);
        options.addOption(k);

        StringJoiner availProperties = new StringJoiner("\n");
        availProperties.add("FirstDictionaryWordInArticle");
        availProperties.add("LastDictionaryWordInArticle" );
        availProperties.add("NumberOfWordsInArticle" );
        availProperties.add("DictionaryWordsInArticle" );
        availProperties.add("NumberOfDictionaryWordsInFirstPartOfArticle" );
        availProperties.add("NumberOfDictionaryWordsInLastPartOfArticle" );
        availProperties.add("MostFrequentDictionaryWord" );
        availProperties.add("LeastFrequentDictionaryWord" );

        String availPropertiesNames = "Avail properties names: \n\n";
        availPropertiesNames += availProperties.toString();

        Option classProperties = new Option("p", "property-class", true, availPropertiesNames);
        classProperties.setRequired(true);
        classProperties.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(classProperties);

        StringJoiner availMetrices = new StringJoiner("\n");
        availMetrices.add("ChebyshevMetric");
        availMetrices.add("EukidesMetric");
        availMetrices.add("ManhattanMetric");
        String availMetricesNames = "Avail metrices names: \n\n\n";
        availMetricesNames += availMetrices.toString();

        Option metrices = new Option("m", "metrices", true, availMetricesNames);
        metrices.setRequired(true);
        options.addOption(metrices);

        StringJoiner availTextSimilarity = new StringJoiner("\n");
        availTextSimilarity.add("HandleDifference");
        availTextSimilarity.add("JaccardSimilarity");
        availTextSimilarity.add("NGramSimilarity");
        availTextSimilarity.add("SimpleStringCompare");
        String availSimilaritiesNames = "Avail text similarities names: \n\n\n";
        availSimilaritiesNames += availTextSimilarity.toString();

        Option similarities = new Option("s", "similarities", true, availSimilaritiesNames);
        similarities.setRequired(true);
        options.addOption(similarities);

        CommandLineParser parser = new BasicParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
            Runner r = new Runner();
            int kVal = Integer.parseInt(cmd.getOptionValue("k"));
            System.out.println("K: " + kVal);
            String[] props = cmd.getOptionValues("p");
            System.out.print("Properties: ");
            for(String s : props) {
                System.out.print(s + ",");
            }
            System.out.println();
            String metric = cmd.getOptionValue("m");
            System.out.println("m: " + metric);
            String similarity = cmd.getOptionValue("s");
            System.out.println("s: " + similarity);
            System.out.println("Found " + r.run(kVal, props, metric, similarity) + "%");
            return;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }
    }

}
