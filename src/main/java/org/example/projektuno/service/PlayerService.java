package org.example.projektuno.service;

import org.example.projektuno.entity.Player;
import org.example.projektuno.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


import java.util.Collection;
import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
    return playerRepository.findAll();
    }

    

    public Player getPlayerById(int id) {
        return playerRepository.findById(id).orElse(null);
    }

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(int id, Player player) {
        if (playerRepository.existsById(id)) {
            player.setId(id);
            return playerRepository.save(player);
        }
        return null;
    }

    public void deletePlayer(int id) {
        playerRepository.deleteById(id);
    }

    public List<Player> getPlayersNotInAnyTeam() {
        return playerRepository.findAll().stream()
                .filter(p -> p.getUserTeams() == null || p.getUserTeams().isEmpty())
                .toList();
    }

}