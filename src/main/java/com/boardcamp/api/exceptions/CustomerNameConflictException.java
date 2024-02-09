package com.boardcamp.api.exceptions;

public class CustomerNameConflictException extends RuntimeException{
    public CustomerNameConflictException(String message){
        super(message);
    }
}
