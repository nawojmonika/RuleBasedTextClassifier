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

        Option showTableHeader = new Option("t", "show-table-header", false, "");
        showTableHeader.setRequired(false);
        options.addOption(showTableHeader);

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
            boolean showTableHead = cmd.hasOption("t");
            int kVal = Integer.parseInt(cmd.getOptionValue("k"));
            if (showTableHead) {
                System.out.println("K;Properties;metric;similarity;num of articles;num of testing set; num of learing set;usa;usa-good;france;france-good;canada;canada-good;west-germany;west-germany-good;uk;uk-good;japan;japan-good;overal");
                System.out.println();
            }
            System.out.print(kVal + ";");
            String[] props = cmd.getOptionValues("p");
            for(String s : props) {
                System.out.print(s + ",");
            }
            System.out.print(";");
            String metric = cmd.getOptionValue("m");
            System.out.print(metric+";");
            String similarity = cmd.getOptionValue("s");
            System.out.print(similarity+";");
            System.out.print(r.run(kVal, props, metric, similarity));
            System.out.println();
            return;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }
    }

}
