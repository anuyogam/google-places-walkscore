package com.di.analytics;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
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
    public String getName() {
        return "property-analytics";
    }

    @Override
    public void initialize(Bootstrap<PropertyAnalyticsConfiguration> bootstrap) {
        bootstrap.addBundle (new AssetsBundle());

    }

    @Override
    public void run(final PropertyAnalyticsConfiguration propertyAnalyticsConfiguration, final Environment environment) throws Exception {
        environment.jersey().register(new PropertyAnalyticsResource());
        //environment.jersey().setUrlPattern("/api/*");
    }
}