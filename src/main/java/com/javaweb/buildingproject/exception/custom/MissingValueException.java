package com.javaweb.buildingproject.exception.custom;

public class MissingValueException extends RuntimeException {
    public MissingValueException(String message) {
        super(message);
    }
}
