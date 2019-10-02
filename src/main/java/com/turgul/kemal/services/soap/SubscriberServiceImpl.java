package com.turgul.kemal.services.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.turgul.kemal.cache.InMemoryCache;
import com.turgul.kemal.model.Subscriber;
import com.turgul.kemal.services.soap.exception.InvalidSubscriberId;
import com.turgul.kemal.services.soap.exception.SubscriberNotFound;

/**
 * @author kemalturgul
 *
 */

@WebService(endpointInterface = "com.turgul.kemal.services.soap.SubscriberService")
public class SubscriberServiceImpl implements SubscriberService {

    private static Logger logger = LogManager.getLogger(SubscriberServiceImpl.class);

    public SubscriberServiceImpl() {
    }

    @WebMethod
    public List<Subscriber> getAllSubscribers() {
        logger.debug("getAllSubscribers [SOAP] is received");
        return InMemoryCache.getInstance().getAllCacheData();
    }

    @WebMethod
    public Subscriber getSubscriberById(int id) throws SubscriberNotFound, InvalidSubscriberId {
        logger.debug("getSubscriberById [SOAP] is received for ID:{}", id);
        if (id <= 0) {
            throw new InvalidSubscriberId();
        }
        Subscriber subscriber = InMemoryCache.getInstance().get(id);

        if (subscriber == null) {
            throw new SubscriberNotFound();
        }

        return subscriber;
    }

}
