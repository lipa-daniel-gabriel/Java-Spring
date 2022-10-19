package com.playtika.java.training.challenge4.lipa.daniel.challenge4.converters;


import com.playtika.java.training.challenge4.lipa.daniel.challenge4.dtos.PlayerDto;
import com.playtika.java.training.challenge4.lipa.daniel.challenge4.models.PlayerProfile;

public class PlayerDTOtoPlayer {

    public static PlayerProfile toPlayer(PlayerDto playerDto) {
        PlayerProfile player = new PlayerProfile();
        player.setUserName(playerDto.getName());
        player.setGender(playerDto.getGender());
        player.setAge(playerDto.getAge());
        return player;
    }
}
