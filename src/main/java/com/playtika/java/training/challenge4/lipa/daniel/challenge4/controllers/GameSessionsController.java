package com.playtika.java.training.challenge4.lipa.daniel.challenge4.controllers;

import com.playtika.java.training.challenge4.lipa.daniel.challenge4.exceptions.CustomExceptionHandler;
import com.playtika.java.training.challenge4.lipa.daniel.challenge4.models.GameSession;
import com.playtika.java.training.challenge4.lipa.daniel.challenge4.serivces.GameSessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sessions")
public class GameSessionsController {

    private final GameSessionsService gameSessionsService;

    @Autowired
    public GameSessionsController(GameSessionsService gameSessionsService) {
        this.gameSessionsService = gameSessionsService;
    }

    @GetMapping
    public ResponseEntity<List<GameSession>> showAllGameSessions() {
        if (gameSessionsService.getAllGameSessions() != null) {
            return new ResponseEntity<>(gameSessionsService.getAllGameSessions(), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public Optional<GameSession> getSession(@PathVariable long id) {
        return gameSessionsService.getGameSessionById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GameSession> deleteSession(@PathVariable long id) {
        if (gameSessionsService.deleteGameSessionById(id)) {
            gameSessionsService.deleteGameSessionById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler({IndexOutOfBoundsException.class,
            UnsupportedOperationException.class, CustomExceptionHandler.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception exception) {
        return exception.getMessage();
    }
}
