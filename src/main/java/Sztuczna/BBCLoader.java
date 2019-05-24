package Sztuczna;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BBCLoader implements Loader{
    private Document nodeParser;
    public ArrayList<Item> loadFile(File file) {
        try {
            this.nodeParser = Jsoup.parse(file, "ISO-8859-1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Item> bbcs = new ArrayList<>();
        for (List<String> record : records) {
            bbcs.add(new BBC(
                    record.get(1),
                    record.get(2)
            ));
        }
        return bbcs;
    }
}
