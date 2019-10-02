package com.turgul.kemal.services.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.turgul.kemal.model.Subscriber;
import com.turgul.kemal.services.soap.exception.InvalidSubscriberId;
import com.turgul.kemal.services.soap.exception.SubscriberNotFound;

/**
 * @author kemalturgul
 *
 */

@WebService
public interface SubscriberService {

    @WebMethod
    public List<Subscriber> getAllSubscribers();

    @WebMethod
    public Subscriber getSubscriberById(int id) throws SubscriberNotFound, InvalidSubscriberId;
}
