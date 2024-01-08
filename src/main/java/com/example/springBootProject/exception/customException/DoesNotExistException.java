package com.example.springBootProject.exception.customException;

public class DoesNotExistException extends RuntimeException{
    public DoesNotExistException(String message) {
        super(message);
    }
}
