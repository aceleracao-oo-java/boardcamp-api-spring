package com.boardcamp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boardcamp.api.models.GameModel;

@Repository
public interface GameRepository extends JpaRepository<GameModel, Long>{
    int gameRepository = 0;

    boolean existsByName(String name);
}
