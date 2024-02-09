package com.boardcamp.api.exceptions;

public class GameOutOfStockException extends RuntimeException {
    public GameOutOfStockException(String message) {
        super(message);
    }
}
