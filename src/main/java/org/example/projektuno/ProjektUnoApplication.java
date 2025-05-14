package org.example.projektuno;

import org.example.projektuno.dataGenerators.LeagueNameGenerator;
import org.example.projektuno.entity.AppUser;
import org.example.projektuno.entity.League;
import org.example.projektuno.entity.Player;
import org.example.projektuno.entity.UserTeam;
import org.example.projektuno.entity.playerChildClasses.Libero;
import org.example.projektuno.entity.playerChildClasses.MiddleBlocker;
import org.example.projektuno.entity.playerChildClasses.OppositeHitter;
import org.example.projektuno.entity.playerChildClasses.OutsideHitter;
import org.example.projektuno.entity.playerChildClasses.ServingSpecialist;
import org.example.projektuno.entity.playerChildClasses.Setter;
import org.example.projektuno.service.AppUserService;
import org.example.projektuno.service.LeagueService;
import org.example.projektuno.service.PlayerService;
import org.example.projektuno.service.UserTeamService;
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
    @Autowired
    private AppUserService userService;
    @Autowired
    private UserTeamService teamService;
 


    public static void main(String[] args) {
        SpringApplication.run(ProjektUnoApplication.class, args);
    }
@EventListener(ApplicationReadyEvent.class)
public void execCodeAfterStartup() {
    if (userService.findByUsername("testuser").isEmpty()) {
        AppUser user = new AppUser("testuser", "test@example.com", "passwort123");
        user = userService.createUser(user);

        UserTeam team = teamService.createTeamForUser("VolleyBangers", user);

        Player p1 = playerService.createPlayer(new OutsideHitter("Max Attack", 50, 20, 10, 3));
        Player p2 = playerService.createPlayer(new Setter("Sally Setter", 25, 100, 15, 5, 2));
        Player p3 = playerService.createPlayer(new Libero("Lenny Libero", 80, 30, 5, 1));

        teamService.addPlayerToTeam(team.getId(), p1.getId());
        teamService.addPlayerToTeam(team.getId(), p2.getId());
        teamService.addPlayerToTeam(team.getId(), p3.getId());

        System.out.println("✅ Testdaten erstellt.");
    } else {
        System.out.println("ℹ️ Testuser existiert bereits – Setup übersprungen.");
    }
}


 
    
}
