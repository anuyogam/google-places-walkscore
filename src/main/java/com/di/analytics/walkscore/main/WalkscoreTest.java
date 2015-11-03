package com.di.analytics.walkscore.main;

/**
 * Created by avenkataraman on 11/2/2015.
 */

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WalkscoreTest {

    private static final String API_KEY = "&wsapikey=ffd1c56f9abcf84872116b4cc2dfcf31";
    private static final String BASE_URL = "http://api.walkscore.com/score?format=json&address=";


    //Constructor - default
    private WalkscoreTest(){
    }

    public static double getWalkScore(JSONObject response){
        double ret = 0;
        try{
            ret = response.getDouble("walkscore");
        }catch(Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    public static JSONObject getWalkScoreResponse(double lat, double lon, String addr)throws Exception{
        JSONObject jsonObject = new JSONObject();
        StringBuilder stringBuilder = new StringBuilder();

        URL url = new URL(BASE_URL+addr+"&lat="+lat+"&lon="+lon+API_KEY);
        URLConnection conn = url.openConnection();
        InputStream response = conn.getInputStream();

        String contentType = conn.getHeaderField("Content-Type");
        String charset = null;

        for (String param : contentType.replace(" ", "").split(";")) {
            if (param.startsWith("charset=")) {
                charset = param.split("=", 2)[1];
                break;
            }
        }

        if (charset != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(response, charset))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    stringBuilder.append(line);
                }
            }
        } else {
            // It's likely binary content, use InputStream/OutputStream.
        }
        try {
            jsonObject = new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    //public static void main(String[] args)throws Exception {

        // URL url = new URL("http://api.walkscore.com/score?format=xml&address=1119%8th%20Avenue%20Seattle%20WA%2098101&lat=47.6085&lon=-122.3295&wsapikey=ffd1c56f9abcf84872116b4cc2dfcf31");

        //getWalkScore(47.6085,-122.3295,"1119%8th%20Avenue%20Seattle%20WA%2098101");

    //}

}


