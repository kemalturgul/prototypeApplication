package com.turgul.kemal.services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonObject;
import com.turgul.kemal.cache.InMemoryCache;
import com.turgul.kemal.constant.Constants;
import com.turgul.kemal.model.Subscriber;
import com.turgul.kemal.util.JsonUtil;
import com.turgul.kemal.util.StringUtil;

/**
 * @author kemalturgul
 *
 */

@Path("/")
public class SubscriberServiceRest extends Application {

    private static Logger logger = LogManager.getLogger(SubscriberServiceRest.class);

    @POST
    @Path("subscriber")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSubscriber(Subscriber subscriber) {
        try {
            logger.info("/subscriber[POST] {}", subscriber);
            if (subscriber == null) {
                logger.warn("Input Subscriber is null");
                return Response.notAcceptable(null).build();
            } else {
                InMemoryCache.getInstance().add(subscriber.getId(), subscriber);
            }

        } catch (Exception e) {
            logger.error("An Exception occurred at REST /subscriber[POST]", e);
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

    @PUT
    @Path("subscriber")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSubscriber(Subscriber subscriber) {
        try {
            logger.info("/subscriber[PUT] {}", subscriber);

            if (subscriber == null) {
                logger.warn("Input Subscriber is null");
                return Response.notAcceptable(null).build();
            } else {
                Subscriber oldSubscriber = InMemoryCache.getInstance().get(subscriber.getId());
                if (oldSubscriber == null) {
                    return Response.notModified(Constants.SUBSCRIBER_NOT_FOUND).build();
                } else {
                    InMemoryCache.getInstance().add(subscriber.getId(), subscriber);
                }
            }

        } catch (Exception e) {
            logger.error("An Exception occurred at REST /subscriber[PUT]", e);
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("subscriber")
    public Response deleteSubscriber(String id) {
        try {
            logger.info("/subscriber[DELETE] {}", id);

            if (StringUtil.isNullOrEmpty(id)) {
                logger.warn("Input Subscriber Id is null or empty");
                return Response.notAcceptable(null).build();
            } else {

                Integer subscriberId = getSubscriberIdFromJson(id);
                Subscriber subscriber = InMemoryCache.getInstance().get(subscriberId);
                if (subscriber == null) {
                    return Response.notModified(Constants.SUBSCRIBER_NOT_FOUND).build();
                } else {
                    InMemoryCache.getInstance().remove(subscriberId);
                }
            }

        } catch (Exception e) {
            logger.error("An Exception occurred at REST /subscriber[DELETE]", e);
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

    private Integer getSubscriberIdFromJson(String id) {
        JsonObject jsonObject = JsonUtil.fromJson(id, JsonObject.class);
        return jsonObject.get("id").getAsInt();
    }

}
