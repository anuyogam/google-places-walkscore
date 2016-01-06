package com.di.analytics;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hughsoong on 2015-11-06.
 */
public class AddrParams {
 /*   {
        "formatted_address": "23 Clarendon Avenue, Toronto, ON, Canada",
            "type": {
        "javaType": "TYPE_AIRPORT",
                "googleType": "airport"
    },
        "maxResults": "50"
    }*/
    @JsonProperty
    private String formattedAddress;
    @JsonProperty
    private PlaceType type;
    @JsonProperty
    private int maxResults;

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public PlaceType getType() {
        return type;
    }

    public int getMaxResults() {
        return maxResults;
    }
}
