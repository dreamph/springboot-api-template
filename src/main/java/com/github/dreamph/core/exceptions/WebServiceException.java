package com.github.dreamph.core.exceptions;

import org.springframework.http.HttpStatus;

public class WebServiceException extends Exception {

    private static final long serialVersionUID = -9095172762000832281L;

    private HttpStatus statusCode;
    private String responseBodyAsString;

    public WebServiceException() {
        super();
    }

    public WebServiceException(HttpStatus statusCode) {
        super();
        this.statusCode = statusCode;
    }

    public WebServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public WebServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebServiceException(String message) {
        super(message);
    }

    public WebServiceException(Throwable cause) {
        super(cause);
    }

    public WebServiceException(Throwable cause, HttpStatus statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }

    public WebServiceException(Throwable cause, HttpStatus statusCode, String responseBodyAsString) {
        super(cause);
        this.statusCode = statusCode;
        this.responseBodyAsString = responseBodyAsString;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
