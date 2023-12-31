package com.example.backend.util;

import com.example.backend.util.exceptions.QuoteNotFoundException;
import com.example.backend.util.exceptions.CustomerNotFoundException;
import com.example.backend.util.exceptions.InvalidRequestException;
import com.example.backend.util.exceptions.ShipmentNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public HttpErrorInfo handleCustomerNotFoundException(WebRequest request, CustomerNotFoundException e) {
        return createHttpErrorInfo(HttpStatus.NOT_FOUND, request, e);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(QuoteNotFoundException.class)
    public HttpErrorInfo handleNotFoundException(WebRequest request, QuoteNotFoundException ex){
        return createHttpErrorInfo(NOT_FOUND, request, ex);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ShipmentNotFoundException.class)
    public HttpErrorInfo handleShipmentNotFoundException(WebRequest request, ShipmentNotFoundException ex){
        return createHttpErrorInfo(NOT_FOUND, request, ex);
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidRequestException.class)
    public HttpErrorInfo handleErrorWhileCreatingAdmin(WebRequest request, InvalidRequestException ex) {
        return createHttpErrorInfo(UNPROCESSABLE_ENTITY, request, ex);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, WebRequest request, Exception ex) {
        final String path = request.getDescription(false);
        final String message = ex.getMessage();

        log.debug("Returning HTTP status: {} for path: {}, message: {}", httpStatus, path, message);

        return new HttpErrorInfo(httpStatus, path, message);
    }
}
