package com.example.backend.shipmentsubdomain.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) { super(message); }
}