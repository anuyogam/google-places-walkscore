package com.di.analytics.walkscore.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by jastang on 11/2/2015.
 */
public class SpotCrimeTest {
    private static final String SPOTCRIME_URL = "http://api.spotcrime.com/crimes.json";
    private static final String SPOTCRIME_KEY = "MLC-restricted-key";
    private static final String ENCODING = java.nio.charset.StandardCharsets.UTF_8.name();

    protected SpotCrimeTest(){
    }

    public void getCrimes(){
        try {
            URLConnection spotcrime = new URL(SPOTCRIME_URL).openConnection();
            spotcrime.setRequestProperty("Accept-Charset", ENCODING);
            spotcrime.setRequestProperty("X-API-Key", SPOTCRIME_KEY);
            InputStream response = spotcrime.getInputStream();

            int status = ((HttpURLConnection)spotcrime).getResponseCode();
            for (Map.Entry<String, List<String>> header : spotcrime.getHeaderFields().entrySet()) {
                System.out.println(header.getKey() + "=" + header.getValue());
            }
            String contentType = spotcrime.getHeaderField("Content-Type");
            String charset = null;

            for (String param : contentType.replace(" ", "").split(";")) {
                if (param.startsWith("charset=")) {
                    charset = param.split("=", 2)[1];
                    break;
                }
            }

            if (charset != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(response, charset))) {
                    for (String line; (line = reader.readLine()) != null;) {
                        System.out.println(line);
                    }
                }
            }
            else {
                // It's likely binary content, use InputStream/OutputStream.
            }
            //InputStream spotcrimeResponse = new URL(SPOTCRIME_URL).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
