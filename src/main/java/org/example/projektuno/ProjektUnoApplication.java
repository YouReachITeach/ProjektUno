package org.example.projektuno;

import org.example.projektuno.entity.League;
import org.example.projektuno.entity.Player;
import org.example.projektuno.service.LeagueService;
import org.example.projektuno.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ProjektUnoApplication {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private LeagueService leagueService;


    public static void main(String[] args) {
        SpringApplication.run(ProjektUnoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void execCodeAfterStartup() {
        Player player = new Player();
        player.setName("John Doe");
        playerService.createPlayer(player);
        League league = new League();
        league.setName("League of Legends");
        leagueService.createLeague(league);
    }
}
