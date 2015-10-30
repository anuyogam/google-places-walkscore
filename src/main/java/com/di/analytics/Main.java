package com.di.analytics;

import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;
import se.walkercrou.places.RequestHandler;

import java.util.List;

/**
 * Created by jastang on 9/23/2015.
 */
public class Main {

    private static final String API_KEY = "AIzaSyBDlQPgnEWUbmvUdvg5sZT2EXeeRg1Er9s";

    private static final String ROOT_URL_JSON_PREFIX = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private static final String URL_SUFFIX = "&key="+API_KEY;


    public static void main(String[] args) {

        GooglePlaces client = new GooglePlaces(API_KEY);
        List<Place> places = client.getNearbyPlaces(43.644, -79.399,550, GooglePlaces.MAXIMUM_RESULTS, Param.name("type").value(GooglePlaces.TYPE_BANK));
        for (Place p : places){
            System.out.println(p.getName() + " " + "adresss: " + p.getAddress());
        }

        RequestHandler rh = client.getRequestHandler();
    }
}
