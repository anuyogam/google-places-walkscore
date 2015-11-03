package com.di.analytics.walkscore.main;

import com.di.analytics.walkscore.main.utilities.GeocodeHelper;
import org.json.JSONObject;
import se.walkercrou.places.GooglePlaces;

import java.util.Scanner;

/**
 * Created by jastang on 9/23/2015.
 */
public class Main {
    private static final String API_KEY = "AIzaSyBDlQPgnEWUbmvUdvg5sZT2EXeeRg1Er9s";
    private static final int MAX_RESULTS = 1;

    public static void main(String[] args) {

        GooglePlaces client = new GooglePlaces(API_KEY);
        GeocodeHelper geocoder = new GeocodeHelper();

        Scanner reader = new Scanner(System.in);
        System.out.println("Where do you live? ");
        String address = reader.nextLine();

        JSONObject response =
                geocoder.geocode(null, geocoder.getFormattedAddressForGeocode(address), GeocodeHelper.GEOCODE_METHOD.FORWARD);
        double lat = geocoder.getLat(response);
        double lng = geocoder.getLong(response);
        //System.out.println("Geocode result: {" + lat +","+lng+"}");
        try {
            System.out.println("Your WalkScore: ");
            System.out.print(WalkscoreTest.getWalkScore(
                    WalkscoreTest.getWalkScoreResponse(lat, lng, geocoder.getFormattedAddressForWalkscore(address))
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
