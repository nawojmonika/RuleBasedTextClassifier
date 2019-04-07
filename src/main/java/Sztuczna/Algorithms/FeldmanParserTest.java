package Sztuczna.Algorithms;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.testng.Assert.*;

public class FeldmanParserTest {

    @org.testng.annotations.Test
    public void testGetParsedWOrdFactLines1() {
        Path p = Paths.get(System.getProperty("user.dir"),"assets", "feldman-cia-worldfactbook-data.txt");
        FeldmanParser fp = new FeldmanParser(p);
        fp.getParsedWOrdFactLines().stream().forEach(s -> System.out.println(s));
    }
}