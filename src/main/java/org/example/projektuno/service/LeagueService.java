package org.example.projektuno.service;

import org.example.projektuno.entity.League;
import org.example.projektuno.entity.Player;
import org.example.projektuno.repositories.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeagueService {
    @Autowired
    private LeagueRepository leagueRepository;

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

    public static boolean addPlayerToLeague(Player player, League league) {
        if (player == null || league == null) {
            return false;
        }

        // Beide Seiten der Beziehung aktualisieren
        if (player.getLeagues() == null) {
            player.setLeagues(new ArrayList<>());
        }
        if (league.getPlayers() == null) {
            league.setPlayers(new ArrayList<>());
        }
        if (player.getLeagues().contains(league)) {
            return false; // Spieler ist bereits in der Liga
        }
        if (league.getPlayers().contains(player)) {
            return false; // Liga enthält bereits den Spieler
        }
        player.getLeagues().add(league);
        league.getPlayers().add(player);
        return true;
    }

    public static boolean removePlayerFromLeague(Player player, League league) {
        if (player == null || league == null) {
            return false;
        }

        // Beide Seiten der Beziehung aktualisieren
        if (player.getLeagues() == null || league.getPlayers() == null) {
            return false;
        }
        if (!player.getLeagues().remove(league)) {
            return false;
        }
        if (!league.getPlayers().remove(player)) {
            return false;
        }
        return true;
    }
}

