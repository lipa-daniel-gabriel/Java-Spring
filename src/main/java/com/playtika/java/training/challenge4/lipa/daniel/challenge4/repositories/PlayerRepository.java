package com.playtika.java.training.challenge4.lipa.daniel.challenge4.repositories;

import com.playtika.java.training.challenge4.lipa.daniel.challenge4.models.PlayerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerProfile, Long> {

    @Query("FROM PlayerProfile x WHERE x.gameSessions.size between :min and :max")
    List<PlayerProfile> findAllByNoPlayedGamesBetween(int min, int max);

    @Query("FROM PlayerProfile x WHERE x.isAcceptingNewsletter = TRUE ORDER BY x.userName DESC")
    List<PlayerProfile> customFindAllByAcceptingNewsletterIsTrueOrderByUserName();

    @Query("FROM PlayerProfile x ORDER BY x.gameSessions.size DESC")
    List<PlayerProfile> bestPlayers();

    List<PlayerProfile> getAllByOrderByUserNameAsc();

}
