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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.query.Jpa21Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
public class ProjektUnoApplication {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private UserTeamRepository userTeamRepository;
    @Autowired
    private LeagueRepository leagueRepository;
    @Autowired
    private AppUserRepository appUserRepository;


    public static void main(String[] args) {
        SpringApplication.run(ProjektUnoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void execCodeAfterStartup() {
        Player JoRauber = new Player("JoRauber", 100);
        Player MoRauber = new Player("MoRauber", 200);
        Player RainerWinkler = new Player("RainerWinkler", 10);
        Player Detlef = new Player("Detlef", 400);
        playerRepository.save(JoRauber);
        playerRepository.save(MoRauber);
        playerRepository.save(RainerWinkler);
        playerRepository.save(Detlef);
        AppUser appUser = new AppUser();
        appUserRepository.save(appUser);
        UserTeam userTeam = new UserTeam("gooners", appUser, 1000);
        userTeamRepository.save(userTeam);
        List<Player> players = new ArrayList<>();
        players.add(JoRauber);
        players.add(MoRauber);
        players.add(RainerWinkler);
        players.add(Detlef);
        League league1 = new League("bundesliga", players);

        System.out.println("map:    ");
        Map<Player, UserTeam> map = league1.getPlayersMap();
        for (Player player : map.keySet()) {
            System.out.println(player.getName());
        }
        leagueRepository.save(league1);
        Optional<League> league = leagueRepository.findById(1);
        System.out.println(league.get().getPlayersMap());
    }
}
