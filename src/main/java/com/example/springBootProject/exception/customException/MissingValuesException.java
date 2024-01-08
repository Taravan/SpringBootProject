package com.example.springBootProject.exception.customException;

public class MissingValuesException extends RuntimeException{
    public MissingValuesException(String message) {
        super(message);
    }
}
