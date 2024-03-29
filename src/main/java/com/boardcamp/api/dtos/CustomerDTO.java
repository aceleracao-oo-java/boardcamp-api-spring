package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
    
    @NotBlank(message = "Name is mandatory!")
    private String name;

    @NotBlank(message = "Cpf is mandatory!")
    @Size(min = 11, max = 11, message = "CPf size must be 11!")
    private String cpf;
}
