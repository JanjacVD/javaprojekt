package com.janjac.exceptions;

public class CredentialsFileException extends Exception{
    public CredentialsFileException() {
    }

    public CredentialsFileException(String message) {
        super(message);
    }

    public CredentialsFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CredentialsFileException(Throwable cause) {
        super(cause);
    }

    public CredentialsFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
