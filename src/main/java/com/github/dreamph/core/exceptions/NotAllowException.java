package com.github.dreamph.core.exceptions;

public class NotAllowException extends RuntimeException {

    public NotAllowException() {
    }

    public NotAllowException(String message) {
        super(message);
    }

    public NotAllowException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAllowException(Throwable cause) {
        super(cause);
    }

    public NotAllowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
