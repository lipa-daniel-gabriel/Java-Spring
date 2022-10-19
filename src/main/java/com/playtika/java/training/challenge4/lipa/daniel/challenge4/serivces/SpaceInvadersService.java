package com.playtika.java.training.challenge4.lipa.daniel.challenge4.serivces;


import com.playtika.java.training.challenge4.lipa.daniel.challenge4.models.GameSession;
import com.playtika.java.training.challenge4.lipa.daniel.challenge4.models.SpaceInvader;
import com.playtika.java.training.challenge4.lipa.daniel.challenge4.repositories.GameSessionsRepository;
import com.playtika.java.training.challenge4.lipa.daniel.challenge4.repositories.SpaceInvaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
public class SpaceInvadersService {

    private final SpaceInvaderRepository spaceInvaderRepository;
    private final GameSessionsRepository gameSessionsRepository;

    @Autowired
    public SpaceInvadersService(SpaceInvaderRepository spaceInvaderRepository,
                                GameSessionsRepository gameSessionsRepository) {
        this.spaceInvaderRepository = spaceInvaderRepository;
        this.gameSessionsRepository = gameSessionsRepository;
    }

    public CompletableFuture<List<SpaceInvader>> generateSpaceInvaders(int no) {
        List<SpaceInvader> invaders = new ArrayList<>();
        for (int i = 0; i < no; i++) {
            SpaceInvader spaceInvader = new SpaceInvader();
            spaceInvader.setX(new Random().nextInt(no * 100));
            spaceInvader.setY(new Random().nextInt(no * 200));
            invaders.add(spaceInvader);
        }
        return CompletableFuture.completedFuture(invaders);
    }

    public Optional<GameSession> getGameSessionById(long id) {
        return gameSessionsRepository.findById(id);
    }
}
