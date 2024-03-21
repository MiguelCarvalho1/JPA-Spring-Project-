package com.miguel.spring.aplication.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {


    public ResourceNotFoundException(Object id) {
        super("Resource not found for id " + id);
    }
}