/*
 */
package com.crossover.techtrial.exceptions;

import org.springframework.http.HttpStatus;

/**
 *
 * @author Ali Imran
 */
public class ApiException extends RuntimeException{
    
    HttpStatus status;

    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ApiException(String message, HttpStatus status, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
    
}
