package com.github.dreamph.core.exceptions;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Optional;


@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleAllExceptions(Exception ex, WebRequest request) {
        LOG.error(ex.getMessage(), ex);
        request.setAttribute(ApiExceptionConstants.APP_EXCEPTION, ex, RequestAttributes.SCOPE_REQUEST);
        return buildErrorResponse("Internal Error:", ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotAllowException.class)
    public ErrorResponse handleNotAllowException(NotAllowException ex, WebRequest request) {
        LOG.error(ex.getMessage(), ex);
        request.setAttribute(ApiExceptionConstants.APP_EXCEPTION, ex, RequestAttributes.SCOPE_REQUEST);
        return buildErrorResponse("Not Allow Error:", ex, request, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ErrorResponse handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        LOG.error(ex.getMessage(), ex);
        request.setAttribute(ApiExceptionConstants.APP_EXCEPTION, ex, RequestAttributes.SCOPE_REQUEST);
        return buildErrorResponse("Unauthorized:", ex, request, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(ValidationException.class)
    public ErrorResponse handleApiException(ValidationException ex, WebRequest request) {
        LOG.error(ex.getMessage(), ex);
        request.setAttribute(ApiExceptionConstants.APP_EXCEPTION, ex, RequestAttributes.SCOPE_REQUEST);
        return buildErrorResponse("Validation : ", ex, request, HttpStatus.BAD_REQUEST);
    }

    ErrorResponse buildErrorResponse(String msg, Throwable e, WebRequest request, HttpStatus httpStatus) {
        ErrorResponse.Builder builder = ErrorResponse.builder(e, httpStatus, e.getMessage());
        final StringBuilder errorMessage = new StringBuilder();
        String message = StringUtils.EMPTY;
        if (e != null) {
            if (e instanceof NullPointerException) {
                message = e.toString();
            } else {
                message = Optional.of(e.getMessage()).orElse(e.getClass().getSimpleName());
            }
        }
        errorMessage.append(msg);
        errorMessage.append(message);

        builder.property("message", errorMessage.toString());
        builder.property("timestamp", LocalDateTime.now());
        builder.property("detail", request.getDescription(true));

        return builder.build();
    }
}
