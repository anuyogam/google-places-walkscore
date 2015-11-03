package com.di.analytics.walkscore.main.utilities;

import org.json.JSONException;
import org.json.JSONObject;
import se.walkercrou.places.Place;
import se.walkercrou.places.exception.GooglePlacesException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by jastang on 11/3/2015.
 */
public class GeocodeHelper {
    private static final String GEO_API_KEY = "AIzaSyCfpRLX1mIkS0DxTSRSU5KnxyTQU86P_ts";

    private static final String ROOT_URL_FORWARD_GEO = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private static final String ROOT_URL_REVERSE_GEO = "https://maps.googleapis.com/maps/api/geocode/json?place_id=";
    private static final String URL_SUFFIX           = "&key=";

    public enum GEOCODE_METHOD {FORWARD, REVERSE};

    private GeocodeHelper(){
    }

    public static String getFormattedAddressForWalkscore(JSONObject response){
        String ret = null;
        ret = getAddress(response).replaceAll(", ", "%");
        return ret;
    }

    public static String getAddress(JSONObject response){
        String ret = null;
        try{
            JSONObject embedded = response.getJSONArray("results").getJSONObject(0);
            ret = embedded.getString("formatted_address");
        }catch(Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    public static double getLat(JSONObject response){
        double ret = 0;
        try{
            JSONObject embedded = response.getJSONArray("results").getJSONObject(0)
                    .getJSONObject("geometry")
                    .getJSONObject("location");
            ret = embedded.getDouble("lat");
        }catch(Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    public static double getLong(JSONObject response){
        double ret = 0;
        try{
            JSONObject embedded = response.getJSONArray("results").getJSONObject(0)
                    .getJSONObject("geometry")
                    .getJSONObject("location");
            ret = embedded.getDouble("lng");
        }catch(Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    public static String getFormattedAddressForGeocode(String address){
        return address.replaceAll(" ", "+");
    }

    //Override for regular call
    public static String getFormattedAddressForWalkscore(String address){
        return address.replaceAll(", ", "%").replaceAll(" ", "%");
    }

    public static JSONObject geocode(Place p, String address, GEOCODE_METHOD method){
        JSONObject jsonObject = new JSONObject();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            URL url = null;

            if(method == GEOCODE_METHOD.REVERSE) {
                url = new URL(ROOT_URL_REVERSE_GEO + p.getPlaceId() + URL_SUFFIX + GEO_API_KEY);
            }else if(method == GEOCODE_METHOD.FORWARD){
                url = new URL(ROOT_URL_FORWARD_GEO + getFormattedAddressForGeocode(address) + URL_SUFFIX + GEO_API_KEY);
            }

            if(url != null) {
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
            }
        } catch (Exception e) {
            throw new GooglePlacesException(e);
        }
        return jsonObject;
    }
}
