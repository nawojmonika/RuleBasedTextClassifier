package Sztuczna.Tests;

import Sztuczna.Algorithms.FeldmanParser;
import Sztuczna.Algorithms.FeldmanProperties;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.testng.Assert.*;

public class FeldmanParserTest {

    @org.testng.annotations.Test
    public void testGetParsedWOrdFactLines1() {
        Path p = Paths.get(System.getProperty("user.dir"),"assets", "feldman-cia-worldfactbook-data.txt");
        FeldmanParser fp = new FeldmanParser(p);
        AssertJUnit.assertArrayEquals(new String[]{"Latvia", "Russia"}, fp.getParsedData("Estonia", FeldmanProperties.LAND_BOUNDERIES).toArray());
        AssertJUnit.assertArrayEquals(new String[]{"1616882"}, fp.getParsedData("Estonia", FeldmanProperties.POPULATION).toArray());
        AssertJUnit.assertArrayEquals(new String[]{"Tallinn"}, fp.getParsedData("Estonia", FeldmanProperties.CAPITAL).toArray());


        AssertJUnit.assertArrayEquals(new String[]{"Andorra","Belgium","Germany","Italy","Luxembourg","Monaco","Spain","Switzerland"}, fp.getParsedData("France", FeldmanProperties.LAND_BOUNDERIES).toArray());
        AssertJUnit.assertArrayEquals(new String[]{}, fp.getParsedData("Germany", FeldmanProperties.INDUSTRIES).toArray());
    }

    @org.testng.annotations.Test
    public void getAllParsedDataForCountry() {
        Path p = Paths.get(System.getProperty("user.dir"),"assets", "feldman-cia-worldfactbook-data.txt");
        FeldmanParser fp = new FeldmanParser(p);
        AssertJUnit.assertArrayEquals(new String[]{"Austria", "Belgium", "Czech Republic", "Denmark", "France", "Luxembourg", "Netherlands", "Poland", "Switzerland", "iron ore", "coal", "potash", "timber", "lignite", "uranium", "copper", "natural gas", "salt", "nickel", "81087506", "Berlin", "AfDB", "AG", "OECD", "PCA", "UN", "UNCTAD", "UNESCO", "UNIDO", "UNHCR", "UNOMIG", "UNOSOM", "UNTAC", "UPU", "WEU", "WHO", "WIPO", "WMO", "WTO", "ZC", "manubgfactures", "EC", "manubgfactures", "agricultural products", "fuels", "raw  materials", "EC"},
                fp.getAllParsedDataForCountry("Germany").toArray());
    }
}