package org.example.projektuno.service;

import jakarta.transaction.Transactional;
import org.example.projektuno.entity.AppUser;
import org.example.projektuno.entity.Player;
import org.example.projektuno.entity.UserTeam;
import org.example.projektuno.repositories.PlayerRepository;
import org.example.projektuno.repositories.UserTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserTeamService {

    @Autowired
    private UserTeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    // Ein neues Team für einen User anlegen
    public UserTeam createTeamForUser(String teamName, AppUser user) {
        UserTeam team = new UserTeam(teamName, user);
        return teamRepository.save(team);
    }

    // Team eines bestimmten Users finden
    public Optional<UserTeam> getTeamByUserId(Long userId) {
        return teamRepository.findByUserId(userId);
    }

    // Spieler zum Team hinzufügen (inkl. Lazy-Fix durch @Transactional)
    @Transactional
    public boolean addPlayerToTeam(Long teamId, int playerId) {
        Optional<UserTeam> teamOpt = teamRepository.findById(teamId);
        Optional<Player> playerOpt = playerRepository.findById(playerId);

        if (teamOpt.isPresent() && playerOpt.isPresent()) {
            UserTeam team = teamOpt.get();
            Player player = playerOpt.get();

            if (!team.getPlayers().contains(player)) {
                team.getPlayers().add(player);
                teamRepository.save(team);
                return true;
            }
        }
        return false;
    }

    // Spieler aus dem Team entfernen
    @Transactional
    public boolean removePlayerFromTeam(Long teamId, int playerId) {
        Optional<UserTeam> teamOpt = teamRepository.findById(teamId);
        Optional<Player> playerOpt = playerRepository.findById(playerId);

        if (teamOpt.isPresent() && playerOpt.isPresent()) {
            UserTeam team = teamOpt.get();
            Player player = playerOpt.get();

            if (team.getPlayers().remove(player)) {
                teamRepository.save(team);
                return true;
            }
        }
        return false;
    }
}
