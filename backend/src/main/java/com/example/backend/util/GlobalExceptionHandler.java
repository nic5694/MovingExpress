package com.example.backend.util;

import com.example.backend.util.exceptions.CustomerNotFoundException;
import com.example.backend.util.exceptions.InvalidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public HttpErrorInfo handleCustomerNotFoundException(CustomerNotFoundException e) {
        return createHttpErrorInfo(HttpStatus.NOT_FOUND, e);
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidRequestException.class)
    public HttpErrorInfo handleErrorWhileCreatingAdmin(InvalidRequestException ex) {
        return createHttpErrorInfo(UNPROCESSABLE_ENTITY, ex);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, Exception ex) {
        final String message = ex.getMessage();

        return new HttpErrorInfo(httpStatus, message);
    }
}
