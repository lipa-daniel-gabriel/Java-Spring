package com.playtika.java.training.challenge4.lipa.daniel.challenge4.controllers;

import com.playtika.java.training.challenge4.lipa.daniel.challenge4.config.GameSettings;
import com.playtika.java.training.challenge4.lipa.daniel.challenge4.exceptions.CustomExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/info")
public class InfoController {

    private GameSettings gameSettings;

    @Autowired
    public InfoController(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }

    @GetMapping
    public Map<String, Object> getInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("version", gameSettings.getVersion());
        map.put("description", gameSettings.getDescription());
        map.put("devName", gameSettings.getDevName());
        map.put("maxNumberOfPlayers", gameSettings.getMaxNumberOfPlayers());
        map.put("lastRun", gameSettings.getLastRunTime()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return map;
    }

    @GetMapping("/version")
    public String getVersion() {
        return gameSettings.getVersion();
    }

    @ExceptionHandler({IndexOutOfBoundsException.class, UnsupportedOperationException.class,
            CustomExceptionHandler.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception exception) {
        return exception.getMessage();
    }
}
