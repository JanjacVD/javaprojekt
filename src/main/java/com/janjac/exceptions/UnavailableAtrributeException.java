package com.janjac.exceptions;

public class UnavailableAtrributeException extends Exception{
    public UnavailableAtrributeException() {
    }

    public UnavailableAtrributeException(String message) {
        super(message);
    }

    public UnavailableAtrributeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnavailableAtrributeException(Throwable cause) {
        super(cause);
    }

    public UnavailableAtrributeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
