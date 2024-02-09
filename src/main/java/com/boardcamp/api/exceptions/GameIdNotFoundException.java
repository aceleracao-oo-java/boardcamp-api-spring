package com.boardcamp.api.exceptions;

public class GameIdNotFoundException extends RuntimeException {
    public GameIdNotFoundException(String message) {
        super(message);
    }
}
