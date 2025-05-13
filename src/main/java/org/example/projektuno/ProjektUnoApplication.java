package org.example.projektuno;

import org.example.projektuno.dataGenerators.LeagueNameGenerator;
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

        for (int i = 0; i < 50; i++) { // Generate 10 random leagues
            League league = new League();
            league.setName(LeagueNameGenerator.generateRandomLeagueName());
            leagueService.createLeague(league);
        }

        Player player = new Player();
        player.setName("John Pork");
        playerService.createPlayer(player);
        for (int i = 0; i < 40; i++) {
            Player player1 = new Player();
            String add = "";
            for (int j = 0; j < i; j++) {
                add += "ab";
            }
            player1.setName("Player " + add);
            playerService.createPlayer(player1);
        }
    }
}
