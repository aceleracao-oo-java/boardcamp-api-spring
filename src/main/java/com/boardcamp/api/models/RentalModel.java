package com.boardcamp.api.models;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.boardcamp.api.dtos.RentalDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class RentalModel {

    public RentalModel(RentalDTO dto) {
        this.daysRented = dto.getDaysRented();
        this.returnDate = null;
        this.delayFee = 0L;
        this.customer = new CustomerModel();
        this.customer.setId(dto.getCustomerId());
        this.game = new GameModel();
        this.game.setId(dto.getGameId());
    }

    public RentalModel(RentalDTO dto, GameModel game, CustomerModel customer, Long price) {
        this.daysRented = dto.getDaysRented();
        this.returnDate = null;
        this.originalPrice = price;
        this.delayFee = 0L;
        this.customer = customer;
        this.game = game;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate rentDate;

    @Column(nullable = false)
    private Long daysRented;

    @Column
    private LocalDate returnDate;

    @Column(nullable = false)
    private Long originalPrice;

    @Column(nullable = false)
    private Long delayFee;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerModel customer;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private GameModel game;

}
