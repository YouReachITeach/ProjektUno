package org.example.projektuno.service;

import org.apache.catalina.User;
import org.example.projektuno.entity.League;
import org.example.projektuno.entity.Player;
import org.example.projektuno.entity.UserTeam;
import org.example.projektuno.repositories.LeagueRepository;
import org.example.projektuno.repositories.PlayerRepository;
import org.example.projektuno.repositories.UserTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeagueService {
    @Autowired
    private LeagueRepository leagueRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private UserTeamRepository teamRepository;


    public List<League> getAllLeagues() {
        return leagueRepository.findAll();
    }

    public League getLeagueById(int id) {
        return leagueRepository.findById(id).orElse(null);
    }

    public League createLeague(League league) {
        return leagueRepository.save(league);
    }

    public League updateLeague(int id, League league) {
        if (leagueRepository.existsById(id)) {
            league.setId(id);
            return leagueRepository.save(league);
        }
        return null;
    }

    public void deleteLeague(int id) {
        leagueRepository.deleteById(id);
    }

    public boolean addPlayerToUserTeam(League league, Player player, UserTeam userTeam) {

        //in the Big Map, check if player is available and add new owner-team to the map
        if (league.getPlayersMap().get(player) == null) {
            league.getPlayersMap().put(player, userTeam);
            leagueRepository.save(league);
            return true;
        }
        return false;
    }


}

