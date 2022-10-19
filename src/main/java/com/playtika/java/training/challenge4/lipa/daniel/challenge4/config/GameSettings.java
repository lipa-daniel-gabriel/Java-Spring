package com.playtika.java.training.challenge4.lipa.daniel.challenge4.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequestMapping("/info")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
@Getter
@Setter
public class GameSettings implements InfoContributor {

    @Value("${app.version:1.2.3}")
    private String version;
    @Value("${app.description:description}")
    private String description;
    @Value("${app.devName:Daniel}")
    private String devName;
    @Value("${app.maxNumberOfPlayers:1000}")
    private int maxNumberOfPlayers;
    private LocalDateTime lastRunTime = LocalDateTime.now();

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("version", version)
                .withDetail("description", description)
                .withDetail("devName", devName)
                .withDetail("maximumNumberOfPlayers", maxNumberOfPlayers)
                .withDetail("lastRun", lastRunTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}

