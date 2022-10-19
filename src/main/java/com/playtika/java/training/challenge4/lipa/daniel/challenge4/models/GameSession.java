package com.playtika.java.training.challenge4.lipa.daniel.challenge4.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "gameSessions")
@Getter
@Setter
public class GameSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "difficulty_level")
    private int difficultyLevel;

    @ManyToOne
    @JoinColumn(name = "player_id")
    @JsonIgnore
    private PlayerProfile playerProfile;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "gameSession_id")
    List<SpaceInvader> spaceInvaders;


}
