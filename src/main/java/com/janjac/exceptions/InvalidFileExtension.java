package com.janjac.exceptions;

public class InvalidFileExtension extends Exception{
    public InvalidFileExtension() {
    }

    public InvalidFileExtension(String message) {
        super(message);
    }

    public InvalidFileExtension(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFileExtension(Throwable cause) {
        super(cause);
    }

    public InvalidFileExtension(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
