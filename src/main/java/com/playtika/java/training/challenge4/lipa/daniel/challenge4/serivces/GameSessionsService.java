package com.playtika.java.training.challenge4.lipa.daniel.challenge4.serivces;

import com.playtika.java.training.challenge4.lipa.daniel.challenge4.models.GameSession;
import com.playtika.java.training.challenge4.lipa.daniel.challenge4.repositories.GameSessionsRepository;
import com.playtika.java.training.challenge4.lipa.daniel.challenge4.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameSessionsService {

    private final GameSessionsRepository gameSessionsRepository;

    @Autowired
    public GameSessionsService(GameSessionsRepository gameSessionsRepository, PlayerRepository playerRepository) {
        this.gameSessionsRepository = gameSessionsRepository;
    }

    public List<GameSession> getAllGameSessions() {
        return gameSessionsRepository.findAll();
    }

    public Optional<GameSession> getGameSessionById(long id) {
        return gameSessionsRepository.findById(id);
    }

    public boolean deleteGameSessionById(long id) {
        Optional<GameSession> gameSessionOptional1 = getGameSessionById(id);
        if (gameSessionOptional1.isEmpty()) {
            return false;
        }
        GameSession gameSession = gameSessionOptional1.get();
        gameSession.getPlayerProfile().getGameSessions().remove(gameSession);
        gameSessionsRepository.deleteById(id);
        Optional<GameSession> gameSessionOptional = getGameSessionById(id);
        if (gameSessionOptional.isPresent()) {
            return false;
        }
        return true;
    }
}
