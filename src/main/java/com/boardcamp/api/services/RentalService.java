package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.exceptions.CustomerNotFoundException;
import com.boardcamp.api.exceptions.GameIdNotFoundException;
import com.boardcamp.api.exceptions.GameOutOfStockException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.repositories.GameRepository;
import com.boardcamp.api.repositories.RentalRepository;

@Service
public class RentalService {

    final RentalRepository rentalRepository;
    final GameRepository gameRepository;
    final CustomerRepository customerRepository;

    public RentalService(RentalRepository rentalRepository, GameRepository gameRepository,
            CustomerRepository customerRepository) {
        this.rentalRepository = rentalRepository;
        this.gameRepository = gameRepository;
        this.customerRepository = customerRepository;
    }

    public RentalModel save(RentalDTO dto) {

        GameModel game = gameRepository.findById(dto.getGameId()).orElseThrow(
                () -> new GameIdNotFoundException("No game found for this Id"));

        CustomerModel customer = customerRepository.findById(dto.getCustomerId()).orElseThrow(
                () -> new CustomerNotFoundException("No customer found for this id"));

        int price = game.getPricePerDay() * dto.getDaysRented();

        List<RentalModel> rentalsByGameId = rentalRepository.findAllByGameId(dto.getGameId());

        if (rentalsByGameId.size() >= game.getStockTotal()) {
            throw new GameOutOfStockException("Selected game is out of stock!");
        }

        RentalModel rental = new RentalModel(dto, game, customer, price);

        return rentalRepository.save(rental);
    }

    public List<RentalModel> findAll() {
        return rentalRepository.findAll();
    }
}
