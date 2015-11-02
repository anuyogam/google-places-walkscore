package com.di.analytics;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by hughsoong on 10/30/2015.
 */
public class PropertyAnalyticsApplication extends Application<PropertyAnalyticsConfiguration> {

    public static void main(String[] args) throws Exception {
        new PropertyAnalyticsApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<PropertyAnalyticsConfiguration> bootstrap) {
        //empty
    }

    @Override
    public void run(PropertyAnalyticsConfiguration propertyAnalyticsConfiguration, Environment environment) throws Exception {
        //empty
    }
}