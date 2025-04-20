package org.example.projektuno.service;

import org.example.projektuno.entity.League;
import org.example.projektuno.repositories.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

