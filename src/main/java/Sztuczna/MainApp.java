package Sztuczna;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.cli.*;

import java.util.Arrays;
import java.util.StringJoiner;


public class MainApp {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
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
        classProperties.setValueSeparator(',');
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
        availTextSimilarity.add("ExtendedNGramSimilarity");
        availTextSimilarity.add("JaccardSimilarity");
        availTextSimilarity.add("NGramSimilarity");
        String availSimilaritiesNames = "Avail text similarities names: \n\n\n";
        availSimilaritiesNames += availTextSimilarity.toString();

        Option similarities = new Option("s", "similarities", true, availSimilaritiesNames);
        similarities.setRequired(true);
        options.addOption(similarities);

        Option loader = new Option("lo", "loader", true, "Loader for specific files");
        loader.setRequired(false);
        options.addOption(loader);

        Option cache = new Option("dc", "dont-read-cache", false, "Dont read dictionary cache");
        cache.setRequired(false);
        options.addOption(cache);

        Option labels = new Option("l", "labels", true, "");
        labels.setArgs(Option.UNLIMITED_VALUES);
        labels.setValueSeparator(',');
        options.addOption(labels);

        Option labelElement = new Option("le", "label-element", true, "");
        options.addOption(labelElement);

        CommandLineParser parser = new BasicParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
            Runner r = new Runner();
            boolean showTableHead = cmd.hasOption("t");
            int kVal = Integer.parseInt(cmd.getOptionValue("k"));
            String[] labelsToWorkOn = new String[]{"usa", "france", "canada", "west-germany", "uk", "japan"};
            if (cmd.hasOption("l")) {
                labelsToWorkOn = cmd.getOptionValues("l");
            }

            String labelElementVal = "country";
            if (cmd.hasOption("le")) {
                labelElementVal = cmd.getOptionValue("le");
            }
            String loaderName = "article";
            if (cmd.hasOption("lo")) {
                loaderName = cmd.getOptionValue("lo");
            }

            boolean readFromCache = true;
            if (cmd.hasOption("dc")) {
                readFromCache = false;
            }
            if (showTableHead) {
                System.out.print("K;Properties;metric;similarity;num of articles;num of testing set; num of learing set;");
                for (String l : labelsToWorkOn) {
                    System.out.print(l + ";" + l+"-good;");
                }
                System.out.print("overal;timeInSeconds");
                System.out.println();
            }

            OutputWriter.addText(""+kVal);
            String[] props = cmd.getOptionValues("p");
            String pr = "" ;
            for(String s : props) {
                pr += s + ",";
            }
            OutputWriter.addText(pr);
            String metric = cmd.getOptionValue("m");
            OutputWriter.addText(metric);
            String similarity = cmd.getOptionValue("s");
            OutputWriter.addText(similarity);
            Double out = r.run(kVal, props, metric, similarity, labelsToWorkOn, labelElementVal, loaderName, readFromCache);
            OutputWriter.addText(""+(System.currentTimeMillis() - start) / 1000);
            OutputWriter.addText(""+out);
            System.out.println(OutputWriter.getString());
            return;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }
    }

}
