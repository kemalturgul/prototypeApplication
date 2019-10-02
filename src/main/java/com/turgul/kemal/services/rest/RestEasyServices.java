package com.turgul.kemal.services.rest;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author kemalturgul
 *
 */

@ApplicationPath("/rest")
public class RestEasyServices extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public RestEasyServices() {
        singletons.add(new SubscriberServiceRest());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

    @Override
    public Set<Class<?>> getClasses() {
        return super.getClasses();
    }

    @Override
    public Map<String, Object> getProperties() {
        return super.getProperties();
    }
}
