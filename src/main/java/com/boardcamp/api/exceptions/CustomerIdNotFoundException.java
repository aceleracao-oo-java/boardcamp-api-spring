package com.boardcamp.api.exceptions;

public class CustomerIdNotFoundException extends RuntimeException {
    public CustomerIdNotFoundException(String message) {
        super(message);
    }
}
