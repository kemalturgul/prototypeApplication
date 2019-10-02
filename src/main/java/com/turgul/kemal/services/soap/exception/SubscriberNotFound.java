package com.turgul.kemal.services.soap.exception;

import javax.xml.ws.WebFault;

import com.turgul.kemal.constant.Constants;

/**
 * @author kemalturgul
 *
 */

@WebFault
public class SubscriberNotFound extends Exception {

    private static final long serialVersionUID = -8218875632746785876L;

    public SubscriberNotFound() {
        super(Constants.SUBSCRIBER_NOT_FOUND);
    }

    public SubscriberNotFound(String message) {
        super(message);
    }

    public SubscriberNotFound(Throwable cause) {
        super(cause);
    }

    public SubscriberNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public SubscriberNotFound(String message, Throwable cause, boolean enableSuppression,
                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
