package com.boardcamp.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.services.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerModel> createCustomer(@RequestBody @Valid CustomerDTO customerDTO){
        CustomerModel customer = customerService.save(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable Long id){
        CustomerModel customer = customerService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }
    
}
