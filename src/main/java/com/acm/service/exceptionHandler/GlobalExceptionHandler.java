package com.acm.service.exceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.Date;

import com.acm.service.exceptionHandler.domain.ErrorResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * Class to handle custom exception handling. All exceptions will get filtered
 * through here.
 * 
 * @author Sam Butler
 * @since 08/08/2020
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles formatting the exception and returning the exception that was thrown.
     * 
     * @param e - the exception that is being thrown
     * @return ResponseEntity of type {@link ErrorResponse}
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e) {
        LOGGER.error("error occurred {}", e);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(BAD_REQUEST);
        errorResponse.setTimestamp(new Date());

        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }
}