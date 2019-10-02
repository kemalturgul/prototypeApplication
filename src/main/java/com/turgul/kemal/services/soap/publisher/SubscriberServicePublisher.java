package com.turgul.kemal.services.soap.publisher;

import javax.xml.ws.Endpoint;

import com.turgul.kemal.services.soap.SubscriberServiceImpl;

/**
 * @author kemalturgul
 *
 */
public class SubscriberServicePublisher {

    public SubscriberServicePublisher() {
    }

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/subscriberservice", new SubscriberServiceImpl());
    }

}
