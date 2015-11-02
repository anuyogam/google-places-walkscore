package com.di.analytics.walkscore.main;

/**
 * Created by avenkataraman on 11/2/2015.
 */

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.net.URL;
import java.net.URLConnection;

public class WalkscoreTest {

    private static final String API_KEY = "&wsapikey=ffd1c56f9abcf84872116b4cc2dfcf31";
    private static final String BASE_URL = "http://api.walkscore.com/score?format=xml&address=";


    //Constructor - default
    public WalkscoreTest(){
    }

    public static void getWalkScore(double lat, double lon, String addr)throws Exception{
        URL url = new URL(BASE_URL+addr+"&lat="+lat+"&lon="+lon+API_KEY);
        URLConnection conn = url.openConnection();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(conn.getInputStream());

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer xform = transformerFactory.newTransformer();

// that’s the default xform; use a stylesheet to get a real one
        xform.transform(new DOMSource(doc), new StreamResult(System.out));

    }

    //public static void main(String[] args)throws Exception {

        // URL url = new URL("http://api.walkscore.com/score?format=xml&address=1119%8th%20Avenue%20Seattle%20WA%2098101&lat=47.6085&lon=-122.3295&wsapikey=ffd1c56f9abcf84872116b4cc2dfcf31");

        //getWalkScore(47.6085,-122.3295,"1119%8th%20Avenue%20Seattle%20WA%2098101");

    //}

}


