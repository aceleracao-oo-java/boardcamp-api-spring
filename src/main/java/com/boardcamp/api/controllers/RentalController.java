package com.boardcamp.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.services.RentalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    final RentalService rentalService;

    public RentalController(RentalService rentalService){
        this.rentalService = rentalService;
    }

    @PostMapping
    public ResponseEntity<RentalModel> createRental(@RequestBody @Valid RentalDTO rentalDTO){
        RentalModel rental = rentalService.save(rentalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(rental);
    }
}