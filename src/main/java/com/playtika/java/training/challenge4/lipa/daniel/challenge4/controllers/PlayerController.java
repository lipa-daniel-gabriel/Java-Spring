package com.playtika.java.training.challenge4.lipa.daniel.challenge4.controllers;

import com.playtika.java.training.challenge4.lipa.daniel.challenge4.dtos.PlayerDto;
import com.playtika.java.training.challenge4.lipa.daniel.challenge4.exceptions.CustomExceptionHandler;
import com.playtika.java.training.challenge4.lipa.daniel.challenge4.models.PlayerProfile;
import com.playtika.java.training.challenge4.lipa.daniel.challenge4.repositories.PlayerRepository;
import com.playtika.java.training.challenge4.lipa.daniel.challenge4.serivces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerController(PlayerService playerService, PlayerRepository playerRepository) {
        this.playerService = playerService;
        this.playerRepository = playerRepository;
    }

    @GetMapping()
    public ResponseEntity<List<PlayerProfile>> getAllPlayers() {
        List<PlayerProfile> allPlayers = playerService.getAllPlayers();
        if (allPlayers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(allPlayers, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PlayerProfile>> getPlayerById(@PathVariable long id) {
        Optional<PlayerProfile> player = playerService.getById(id);
        if (player.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<PlayerProfile>> getAllPlayersWithAscByName() {
        return playerService.getAllPlayersAscByName();
    }

    @GetMapping("/halloffame")
    public List<PlayerProfile> getPlayersDescendingByNoPlayedGame() {
        return playerService.getPlayersDescendingNoPlayedGames();
    }

    @GetMapping(params = {"min", "max"})
    public ResponseEntity<List<PlayerProfile>> getBetweenMinAndMaxPlayedGames(@RequestParam(name = "min") int min,
                                                                              @RequestParam(name = "max") int max) {
        List<PlayerProfile> playerProfiles = playerService.getAllBetweenLimits(min, max);
        if (!playerProfiles.isEmpty()) {
            return new ResponseEntity<>(playerProfiles, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/register")
    public ResponseEntity<PlayerProfile> playerRegistration(@RequestBody PlayerProfile player) {
        if (player.getId() != null) {
            return new ResponseEntity<>(playerService.addPlayer(player), HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.TOO_EARLY);
    }

    @PatchMapping("{id}/patch")
    public ResponseEntity<PlayerProfile> updateNameGenderAndAge(@PathVariable long id, @RequestBody PlayerDto playerDto) {
        return playerService.updateNameAgeAndGender(id, playerDto)
                .map(player -> new ResponseEntity<>(player, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/unregister/{id}")
    public ResponseEntity<PlayerProfile> unregisterPlayer(@PathVariable long id) {
        if (playerService.delete(id)) {
            playerService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}/newsletter/register")
    public ResponseEntity<PlayerProfile> registerToNewsletter(@PathVariable long id) {
        PlayerProfile playerProfile = playerService.subscribeToNews(id);
        return new ResponseEntity<>(playerProfile, HttpStatus.OK);
    }

    @PatchMapping("/{id}/newsletter/unregister")
    public ResponseEntity<PlayerProfile> unregisterToNewsletter(@PathVariable long id) {
        PlayerProfile playerProfile = playerService.unsubscribeToNews(id);
        return new ResponseEntity<>(playerProfile, HttpStatus.OK);
    }

    @ExceptionHandler({CustomExceptionHandler.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String handleException(Exception exception) {
        return exception.getMessage();
    }
}
