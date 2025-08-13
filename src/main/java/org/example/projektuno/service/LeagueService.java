package org.example.projektuno.service;


import org.example.projektuno.entity.League;
import org.example.projektuno.entity.Player;
import org.example.projektuno.entity.UserTeam;
import org.example.projektuno.repositories.LeagueRepository;
import org.example.projektuno.repositories.PlayerRepository;
import org.example.projektuno.repositories.UserTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LeagueService {
    @Autowired
    private LeagueRepository leagueRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private UserTeamRepository teamRepository;


    //CRUD
    public List<League> getAllLeagues() {
        return leagueRepository.findAll();
    }

    public League getLeagueById(int id) {
        return leagueRepository.findById(id).orElse(null);
    }

    public League createLeague(String name) {
        League league = new League(name, new HashSet<>());
        return leagueRepository.save(league);
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


    //Special:
    public boolean addPlayerToUserTeam(League league, Player player) {
        if (league.getFreePlayersSet().contains(player)) {
            league.getFreePlayersSet().remove(player);
            leagueRepository.save(league);
            return true;
        }
        return false;
    }

    public boolean deletePlayerFromUserTeam(League league, Player player) {
        if (league.getFreePlayersSet().add(player)) {
            leagueRepository.save(league);
            return true;
        }
        return false;
    }

    public Set<Player> getFreePlayers(int leagueId) {
        League league = leagueRepository.findById(leagueId).orElse(null);
        if (league != null) {
            return league.getFreePlayersSet();
        } else {
            return null;
        }
    }


}

