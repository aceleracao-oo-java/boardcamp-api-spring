package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentalDTO {
    
    @NotNull(message = "customerId is mandatory!")
    private Long customerId;

    @NotNull(message = "gameId is mandatory!")
    private Long gameId;

    @NotNull(message = "daysRented is mandatory!")
    private int daysRented;
}
