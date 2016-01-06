package com.di.analytics;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hughsoong on 2015-11-06.
 */
public class PlaceType {
    @JsonProperty
    private String javaType;
    @JsonProperty
    private String googleType;

    public String getJavaType() {
        return javaType;
    }

    public String getGoogleType() {
        return googleType;
    }
}
