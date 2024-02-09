package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Image is mandatory")
    private String image;

    @NotNull
    private int stockTotal;

    @NotNull
    private int pricePerDay;

}
