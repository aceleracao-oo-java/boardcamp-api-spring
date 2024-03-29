package com.boardcamp.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Game
    @ExceptionHandler({ GameNameConflictException.class })
    public ResponseEntity<String> handleGameNameConflictException(GameNameConflictException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    // Customer
    @ExceptionHandler({ CustomerNameConflictException.class })
    public ResponseEntity<String> handleCustomerNameConflictException(CustomerNameConflictException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler({ CustomerNotFoundException.class })
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    // Rentals

    @ExceptionHandler({ GameIdNotFoundException.class })
    public ResponseEntity<String> handleGameIdNotFoundException(GameIdNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({ GameOutOfStockException.class })
    public ResponseEntity<String> handleGameOutOfStockException(GameOutOfStockException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }

    @ExceptionHandler({ RentalNotFoundException.class })
    public ResponseEntity<String> handleRentalNotFoundException(RentalNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({ RentalFinishedException.class })
    public ResponseEntity<String> handleRentalFinishedException(RentalFinishedException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }

}
