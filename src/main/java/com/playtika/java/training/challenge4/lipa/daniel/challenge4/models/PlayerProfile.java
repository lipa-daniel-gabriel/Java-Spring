package com.playtika.java.training.challenge4.lipa.daniel.challenge4.models;


import com.playtika.java.training.challenge4.lipa.daniel.challenge4.dtos.PlayerDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "players")
@Getter
@Setter
public class PlayerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "player_username")
    private String userName;
    @Column(name = "player_email")
    private String gender;
    private int age;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "playerProfile")
    private Set<GameSession> gameSessions = new HashSet<>();
    @Column(name = "player_no_played_games")
    private int noPlayedGames;
    @Column(name = "player_accepts_newsletter", nullable = false)
    private boolean isAcceptingNewsletter;

    public PlayerProfile withModifiesBy(PlayerDto playerDto) {
        setUserName(playerDto.getName());
        setAge(playerDto.getAge());
        setGender(playerDto.getGender());
        return this;
    }
}
