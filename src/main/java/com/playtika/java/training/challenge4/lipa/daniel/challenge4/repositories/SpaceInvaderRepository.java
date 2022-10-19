package com.playtika.java.training.challenge4.lipa.daniel.challenge4.repositories;

import com.playtika.java.training.challenge4.lipa.daniel.challenge4.models.SpaceInvader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceInvaderRepository extends JpaRepository<SpaceInvader, Long> {
}
