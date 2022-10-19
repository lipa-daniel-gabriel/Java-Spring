package com.playtika.java.training.challenge4.lipa.daniel.challenge4.serivces;

import com.playtika.java.training.challenge4.lipa.daniel.challenge4.dtos.PlayerDto;
import com.playtika.java.training.challenge4.lipa.daniel.challenge4.models.PlayerProfile;
import com.playtika.java.training.challenge4.lipa.daniel.challenge4.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerProfile> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Optional<PlayerProfile> getById(long id) {
        return playerRepository.findById(id);
    }

    public ResponseEntity<List<PlayerProfile>> getAllPlayersAscByName() {
        if (playerRepository.getAllByOrderByUserNameAsc().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerRepository.getAllByOrderByUserNameAsc(), HttpStatus.OK);
    }

    public List<PlayerProfile> getPlayersDescendingNoPlayedGames() {
        return playerRepository.bestPlayers();
    }

    public List<PlayerProfile> getAllBetweenLimits(int min, int max) {
        return playerRepository.findAllByNoPlayedGamesBetween(min, max);
    }

    public PlayerProfile addPlayer(PlayerProfile player) {
        PlayerProfile playerProfile = new PlayerProfile();
        if (player.getAge() > 18 || !playerRepository.existsById(player.getId())) {
            playerProfile = playerRepository.saveAndFlush(player);
        }
        return playerProfile;
    }

    public Optional<PlayerProfile> updateNameAgeAndGender(long id, PlayerDto playerDto) {
        return playerRepository.findById(id)
                .map(player -> player.withModifiesBy(playerDto))
                .map(playerRepository::saveAndFlush);
    }

    public boolean delete(long id) {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public PlayerProfile subscribeToNews(long id) {
        PlayerProfile playerProfile1 = new PlayerProfile();
        Optional<PlayerProfile> playerProfile = playerRepository.findById(id);
        if (playerProfile.isPresent()) {
            playerProfile1 = playerProfile.get();
            playerProfile1.setAcceptingNewsletter(true);
            playerRepository.saveAndFlush(playerProfile1);
        }
        return playerProfile1;
    }

    public PlayerProfile unsubscribeToNews(long id) {
        PlayerProfile playerProfile1 = new PlayerProfile();
        Optional<PlayerProfile> playerProfile = playerRepository.findById(id);
        if (playerProfile.isPresent()) {
            playerProfile1 = playerProfile.get();
            playerProfile1.setAcceptingNewsletter(false);
            playerRepository.saveAndFlush(playerProfile1);
        }
        return playerProfile1;
    }
}

