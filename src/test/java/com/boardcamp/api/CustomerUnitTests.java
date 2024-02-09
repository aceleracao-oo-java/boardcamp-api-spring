package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.exceptions.CustomerNameConflictException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.services.CustomerService;

@SpringBootTest(classes = CustomerUnitTests.class)
class CustomerUnitTests {

	@InjectMocks
	private CustomerService customerService;

	@Mock
	CustomerRepository customerRepository;

	@Test
	void givenExistentCustomerCpf_whenCreatingCustomer_thenThrowsError() {
		CustomerDTO customerDTO = new CustomerDTO("Nome", "01234567890");
		doReturn(true).when(customerRepository).existsByCpf(any());

		CustomerNameConflictException exception = assertThrows(CustomerNameConflictException.class,
				() -> customerService.save(customerDTO));

		verify(customerRepository, times(1)).existsByCpf(any());
		verify(customerRepository, times(0)).save(any());
		assertNotNull(exception);
		assertEquals("Customer CPF already in use", exception.getMessage());
	}

	@Test
	void givenValidCustomerCpf_whenCreatingCustomer_thenCreate() {
		CustomerDTO customerDTO = new CustomerDTO("Nome", "01234567890");
		CustomerModel newCustomer = new CustomerModel(customerDTO);

		doReturn(false).when(customerRepository).existsByCpf(any());
		doReturn(newCustomer).when(customerRepository).save(any());

		CustomerModel result = customerService.save(customerDTO);

		verify(customerRepository, times(1)).existsByCpf(any());
		verify(customerRepository, times(1)).save(any());
		assertEquals(newCustomer, result);
	}

	@Test
	void givenValidCustomerId_whenSearchingExistingUser_thenFindUser() {
		Long customerId = 1L;
		CustomerModel customerModel = new CustomerModel();
		customerModel.setId(customerId);
		Optional<CustomerModel> optionalCustomer = Optional.of(customerModel);

		Mockito.when(customerRepository.findById(customerId)).thenReturn(optionalCustomer);

		CustomerModel result = customerService.findById(customerId);

		Mockito.verify(customerRepository, Mockito.times(1)).findById(customerId);

		assertNotNull(result);
		assertEquals(customerId, result.getId());
	}
}
