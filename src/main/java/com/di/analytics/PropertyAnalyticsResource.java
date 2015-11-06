package com.di.analytics;


import com.di.analytics.walkscore.main.utilities.GeocodeHelper;
import org.json.JSONObject;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * Created by hughsoong on 10/30/2015.
 */
@Path("/property")
@Produces(MediaType.APPLICATION_JSON)
public class PropertyAnalyticsResource {
    private static final String API_KEY = "AIzaSyCfpRLX1mIkS0DxTSRSU5KnxyTQU86P_ts";

    @Path("lookup")
    @POST()
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Place> lookupAddress(AddrParams params){

        JSONObject response =
                GeocodeHelper.geocode(null, GeocodeHelper.getFormattedAddressForGeocode(params.getFormattedAddress()), GeocodeHelper.GEOCODE_METHOD.FORWARD);
        double lat = GeocodeHelper.getLat(response);
        double lng = GeocodeHelper.getLong(response);
        GooglePlaces client = new GooglePlaces(API_KEY);
        client.setDebugModeEnabled(false);
        List<Place> places = client.getNearbyPlaces(lat, lng, 1000, Param.name("type").value(params.getType().getGoogleType()));

        return places;
    }


}
