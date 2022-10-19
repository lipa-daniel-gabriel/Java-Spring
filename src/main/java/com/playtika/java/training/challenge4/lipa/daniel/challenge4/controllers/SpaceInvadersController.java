package com.playtika.java.training.challenge4.lipa.daniel.challenge4.controllers;

import com.playtika.java.training.challenge4.lipa.daniel.challenge4.exceptions.CustomExceptionHandler;
import com.playtika.java.training.challenge4.lipa.daniel.challenge4.models.GameSession;
import com.playtika.java.training.challenge4.lipa.daniel.challenge4.models.SpaceInvader;
import com.playtika.java.training.challenge4.lipa.daniel.challenge4.serivces.SpaceInvadersService;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class SpaceInvadersController {

    private final SpaceInvadersService spaceInvadersService;

    public SpaceInvadersController(SpaceInvadersService spaceInvadersService) {
        this.spaceInvadersService = spaceInvadersService;
    }

    @GetMapping("/invaders/generate")
    public List<SpaceInvader> getGeneratedSpaceInvaders(@RequestParam int numberOfInvaders) {
        CompletableFuture<List<SpaceInvader>> space = spaceInvadersService.generateSpaceInvaders(numberOfInvaders);
        try {
            return space.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new CustomExceptionHandler("Thread not working");
        }
    }

    @PostMapping("play/{id}/invaders")
    public ResponseEntity<GameSession> addSpaceInvadersToList(@PathVariable long id,
                                                              @RequestParam(name = "noSpaceInvaders") int noSpaceInvaders)
            throws ExecutionException, InterruptedException {
        List<SpaceInvader> generatedList = spaceInvadersService.generateSpaceInvaders(noSpaceInvaders).get();
        Optional<GameSession> gameSessionOptional = spaceInvadersService.getGameSessionById(id);
        if (gameSessionOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        GameSession gameSession = gameSessionOptional.get();
        for (SpaceInvader spaceInvader : generatedList) {
            gameSession.getSpaceInvaders().add(spaceInvader);
        }
        return new ResponseEntity<>(gameSession, HttpStatus.OK);
    }

    @ExceptionHandler({CustomExceptionHandler.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String handleException(Exception exception) {
        return exception.getMessage();
    }
}
