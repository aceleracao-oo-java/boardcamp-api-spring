package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.dtos.GameDTO;
import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.repositories.GameRepository;
import com.boardcamp.api.repositories.RentalRepository;
import com.boardcamp.api.services.RentalService;

@SpringBootTest(classes = RentalsUnitTests.class)
class RentalsUnitTests {

    @InjectMocks
    private RentalService rentalService;

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private GameRepository gameRepository;

    @Test
    void givenValidRentalBody_whenCreatingARental_thenCreates() {
        GameDTO gameDTO = new GameDTO("teste", "teste", 3, 1500);
        GameModel game = new GameModel(gameDTO);

        CustomerDTO customerDTO = new CustomerDTO("teste", "01234567890");
        CustomerModel customer = new CustomerModel(customerDTO);

        RentalDTO rentalDTO = new RentalDTO(customer.getId(), game.getId(), 3L);
        Long price = game.getPricePerDay() * rentalDTO.getDaysRented();
        RentalModel rental = new RentalModel(rentalDTO, game, customer, price);

        doReturn(Optional.of(game)).when(gameRepository).findById(game.getId());
        doReturn(Optional.of(customer)).when(customerRepository).findById(customer.getId());
        doReturn(rental).when(rentalRepository).save(any());

        RentalModel result = rentalService.save(rentalDTO);

        verify(gameRepository, times(1)).findById(any());
        verify(customerRepository, times(1)).findById(any());
        verify(rentalRepository, times(1)).save(any());
        assertEquals(rental, result);

    }
}
