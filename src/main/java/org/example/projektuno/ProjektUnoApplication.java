package org.example.projektuno;

import org.example.projektuno.dataGenerators.LeagueNameGenerator;
import org.example.projektuno.entity.League;
import org.example.projektuno.entity.Player;
import org.example.projektuno.entity.playerChildClasses.OutsideHitter;
import org.example.projektuno.entity.playerChildClasses.ServingSpecialist;
import org.example.projektuno.entity.playerChildClasses.Setter;
import org.example.projektuno.service.LeagueService;
import org.example.projektuno.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Random;

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

//        for (int i = 0; i < 50; i++) { // Generate 10 random leagues
//            League league = new League();
//            league.setName(LeagueNameGenerator.generateRandomLeagueName());
//            leagueService.createLeague(league);
//        }
//
//        Player player = new Player();
//        player.setName("John Pork");
//        playerService.createPlayer(player);
//        for (int i = 0; i < 40; i++) {
//            Player player1 = new Player();
//            String add = "";
//            for (int j = 0; j < i; j++) {
//                add += "ab";
//            }
//            player1.setName("Player " + add);
//            playerService.createPlayer(player1);
//        }


//        Random random = new Random();
//
//        // Erstelle 50 Spieler
//        for (int i = 0; i < 50; i++) {
//            int playerType = random.nextInt(3); // 0 = OutsideHitter, 1 = ServingSpecialist, 2 = Setter
//
//            switch (playerType) {
//                case 0 -> {
//                    OutsideHitter outsideHitter = new OutsideHitter(
//                            "Outside Hitter " + i,
//                            random.nextInt(100), // spikes
//                            random.nextInt(50),  // blocks
//                            random.nextInt(30),  // serviceAces
//                            random.nextInt(20)   // attackErrors
//                    );
//                    playerService.createPlayer(outsideHitter);
//                }
//                case 1 -> {
//                    ServingSpecialist servingSpecialist = new ServingSpecialist(
//                            "Serving Specialist " + i,
//                            random.nextInt(50),  // serviceAces
//                            random.nextInt(20),  // serviceErrors
//                            random.nextInt(100)  // servicePoints
//                    );
//                    playerService.createPlayer(servingSpecialist);
//                }
//                case 2 -> {
//                    Setter setter = new Setter(
//                            "Setter " + i, i,
//                            random.nextInt(100), // assists
//                            random.nextInt(50),  // serviceAces
//                            random.nextInt(30),  // blocks
//                            random.nextInt(20)   // settingErrors
//                    );
//                    playerService.createPlayer(setter);
//                }
//            }
//        }
    }
}
