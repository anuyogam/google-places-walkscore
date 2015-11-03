package com.di.analytics.walkscore.main;

import com.di.analytics.walkscore.main.utilities.GeocodeHelper;
import org.json.JSONObject;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;

import java.util.List;
import java.util.Scanner;

/**
 * Created by jastang on 9/23/2015.
 */
public class Main {
    private static final String API_KEY = "AIzaSyBDlQPgnEWUbmvUdvg5sZT2EXeeRg1Er9s";
    private static final int MAX_RESULTS = 3;

    public static void main(String[] args) {
        GooglePlaces client = new GooglePlaces(API_KEY);
        client.setDebugModeEnabled(false);

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
            System.out.println(WalkscoreTest.getWalkScore(
                    WalkscoreTest.getWalkScoreResponse(lat, lng, geocoder.getFormattedAddressForWalkscore(address))
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("What do you want to find nearby? ");
        String placeType = reader.nextLine();
        System.out.println("Within how many meters? ");
        double radius = reader.nextDouble();
        List<Place> places = client.getNearbyPlaces(lat, lng, radius, Param.name("type").value(placeType.toLowerCase().replaceAll(" ", "_")));
        for(Place p : places){
            System.out.println(p.getName());
            JSONObject reverseGeocode = geocoder.geocode(p, null, GeocodeHelper.GEOCODE_METHOD.REVERSE);
            System.out.println("Address: " + geocoder.getAddress(reverseGeocode) + '\n');
        }
    }
}
