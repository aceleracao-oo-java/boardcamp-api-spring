package com.boardcamp.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boardcamp.api.models.RentalModel;

@Repository
public interface RentalRepository extends JpaRepository<RentalModel, Long> {

    @Query(value = "SELECT * FROM rentals WHERE rentals.game_id = :gameId AND rentals.return_date IS NULL", nativeQuery = true)
    List<RentalModel> findAllByGameId(@Param("gameId") Long gameId);
}
