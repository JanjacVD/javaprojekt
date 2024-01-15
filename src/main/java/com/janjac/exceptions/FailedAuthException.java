package com.janjac.exceptions;

public class FailedAuthException extends Exception{
    public FailedAuthException() {
    }

    public FailedAuthException(String message) {
        super(message);
    }

    public FailedAuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedAuthException(Throwable cause) {
        super(cause);
    }

    public FailedAuthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
