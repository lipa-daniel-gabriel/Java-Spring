package com.playtika.java.training.challenge4.lipa.daniel.challenge4.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = "spaceinvaders")
@Getter
@Setter
public class SpaceInvader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "coordinate_x")
    private int X;
    @Column(name = "coordinate_y")
    private int Y;


}
