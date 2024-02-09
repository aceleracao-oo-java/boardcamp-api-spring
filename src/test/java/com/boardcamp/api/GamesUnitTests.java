package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.boardcamp.api.dtos.GameDTO;
import com.boardcamp.api.exceptions.GameNameConflictException;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.GameRepository;
import com.boardcamp.api.services.GameService;

@SpringBootTest(classes = GamesUnitTests.class)
public class GamesUnitTests {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @Test
    void givenExistentGameName_whenCreatingGame_thenThrowsError() {
        GameDTO gameDTO = new GameDTO("test", "teste", 3, 1500);

        doReturn(true).when(gameRepository).existsByName(any());
        GameNameConflictException exception = assertThrows(GameNameConflictException.class,
                () -> gameService.save(gameDTO));

        verify(gameRepository, times(1)).existsByName(any());
        verify(gameRepository, times(0)).save(any());
        assertNotNull(exception);
        assertEquals("Game name already exists", exception.getMessage());
    }

    @Test
    void givenValidGameName_whenCreatingGame_thenCreate() {
        GameDTO gameDTO = new GameDTO("test", "teste", 3, 1500);
        GameModel newGame = new GameModel(gameDTO);

        doReturn(false).when(gameRepository).existsByName(any());
        doReturn(newGame).when(gameRepository).save(any());

        GameModel result = gameService.save(gameDTO);

        verify(gameRepository, times(1)).existsByName(any());
        verify(gameRepository, times(1)).save(any());
        assertEquals(newGame, result);
    }

}
