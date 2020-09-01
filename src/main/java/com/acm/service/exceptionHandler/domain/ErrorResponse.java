package com.acm.service.exceptionHandler.domain;

import java.util.Date;

import org.springframework.http.HttpStatus;

/**
 * Error object to help exceptions be more common and cleaner when they are
 * thrown.
 * 
 * @author Sam Butler
 * @since 08/08/2020
 */
public class ErrorResponse {
    private Date timestamp;
    private String message;
    private HttpStatus status;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}