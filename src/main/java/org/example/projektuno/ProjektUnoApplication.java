package org.example.projektuno;

import jakarta.transaction.Transactional;
import org.example.projektuno.entity.AppUser;
import org.example.projektuno.entity.League;
import org.example.projektuno.entity.Player;
import org.example.projektuno.entity.UserTeam;
import org.example.projektuno.repositories.AppUserRepository;
import org.example.projektuno.repositories.LeagueRepository;
import org.example.projektuno.repositories.PlayerRepository;
import org.example.projektuno.repositories.UserTeamRepository;
import org.example.projektuno.service.AppUserService;
import org.example.projektuno.service.LeagueService;
import org.example.projektuno.service.PlayerService;
import org.example.projektuno.service.UserTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.query.Jpa21Utils;

import java.util.*;

@SpringBootApplication
public class ProjektUnoApplication {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private UserTeamService userTeamService;
    @Autowired
    private LeagueService leagueService;
    @Autowired
    private AppUserService appUserService;


    public static void main(String[] args) {
        SpringApplication.run(ProjektUnoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void execCodeAfterStartup() {
        Player JoRauber = new Player("JoRauber", 10000);
        Player MoRauber = new Player("MoRauber", 200);
        Player RainerWinkler = new Player("RainerWinkler", 10);
        Player Detlef = new Player("Detlef", 400);
        JoRauber = playerService.createPlayer(JoRauber);
        MoRauber = playerService.createPlayer(MoRauber);
        RainerWinkler = playerService.createPlayer(RainerWinkler);
        Detlef = playerService.createPlayer(Detlef);
        AppUser appUser = new AppUser();
        appUser = appUserService.createUser(appUser);

        Set<Player> players = new HashSet<>();
        players.add(JoRauber);
        players.add(MoRauber);
        players.add(RainerWinkler);
        players.add(Detlef);
        League league1 = new League("bundesliga", players);
        league1 = leagueService.createLeague(league1);

        UserTeam userTeam = userTeamService.createUserTeam(appUser.getId(), league1.getId(), "DieAmateure");

        System.out.println("Gesichert: " + league1.getFreePlayersSet());

        League league = leagueService.getLeagueById(1);
        if (league != null) {
            System.out.println(league.getFreePlayersSet());
        }
    }
}
