package com.turgul.kemal.services.soap.exception;

import javax.xml.ws.WebFault;

import com.turgul.kemal.constant.Constants;

/**
 * @author kemalturgul
 *
 */

@WebFault
public class InvalidSubscriberId extends Exception {

    private static final long serialVersionUID = 1131359632648332682L;

    public InvalidSubscriberId() {
        super(Constants.SUBSCRIBER_ID_INVALID);
    }

    public InvalidSubscriberId(String message) {
        super(message);
    }

    public InvalidSubscriberId(Throwable cause) {
        super(cause);
    }

    public InvalidSubscriberId(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSubscriberId(String message, Throwable cause, boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
