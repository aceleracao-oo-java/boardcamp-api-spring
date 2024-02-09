package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameDTO {
    
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Image is mandatory")
    private String image;

    @NotBlank(message = "StockTotal is mandatory")
    private int stockTotal;

    @NotBlank(message = "PricePerDay is mandatory")
    private int pricePerDay;

}
