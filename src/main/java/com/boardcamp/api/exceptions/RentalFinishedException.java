package com.boardcamp.api.exceptions;

public class RentalFinishedException extends RuntimeException {
    public RentalFinishedException(String message) {
        super(message);
    }
}
