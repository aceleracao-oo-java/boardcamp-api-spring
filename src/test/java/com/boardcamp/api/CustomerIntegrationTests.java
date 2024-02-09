package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.repositories.CustomerRepository;

@SpringBootTest(classes = CustomerIntegrationTests.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CustomerIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @AfterEach
    void cleanUpDb() {
        customerRepository.deleteAll();
    }

    @Test
    void givenValidCustomerCpf_whenCreatingCustomer_thenCreate() {
        CustomerDTO customerDTO = new CustomerDTO("Nome", "01234567890");

        HttpEntity body = new HttpEntity<>(customerDTO);

        ResponseEntity<CustomerModel> response = restTemplate.exchange(
                "/customers",
                HttpMethod.POST,
                body,
                CustomerModel.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
