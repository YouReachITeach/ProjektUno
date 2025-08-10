package org.example.projektuno.service;

import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.example.projektuno.entity.AppUser;
import org.example.projektuno.entity.League;
import org.example.projektuno.entity.Player;
import org.example.projektuno.entity.UserTeam;
import org.example.projektuno.repositories.LeagueRepository;
import org.example.projektuno.repositories.PlayerRepository;
import org.example.projektuno.repositories.UserTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserTeamService {

    @Autowired
    private UserTeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private LeagueService leagueService;


    // CRUD
    public UserTeam createTeamForUser(UserTeam userTeam) {
        return teamRepository.save(userTeam);
    }

    public UserTeam updateTeam(long id, UserTeam userTeam) {
        if (teamRepository.existsById(id)) {
            userTeam.setId(id);
            return teamRepository.save(userTeam);
        }
        return null;
    }

    public void deleteTeam(long id) {
        teamRepository.deleteById(id);
    }

    public List<UserTeam> getAllUserTeams() {
        return teamRepository.findAll();
    }

    public List<UserTeam> getAllTeamsForUser(long id) {
        List<UserTeam> teams = teamRepository.findAll();
        return teams.stream().filter(team -> team.getUser().getId() == id).toList();

    }


    //Special Methods
    @Transactional
    public boolean addPlayerToUserTeam(Long teamId, int playerId, boolean verrechnen) {
        //get player, userTeam and the teams league from the DB
        Optional<UserTeam> team = teamRepository.findById(teamId);
        if (team.isEmpty()) return false;
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if (playerOptional.isEmpty()) return false;
        UserTeam userTeam = team.get();
        Player player = playerOptional.get();
        League league = userTeam.getLeague(); // Now safe to access - league is already loaded

        //budget check nur wenn verrechnet wird
        if ((userTeam.getBudget() < player.getPrice()) && verrechnen) return false;

        //if team already owns this player, abort
        if (userTeam.getPlayers().contains(player)) return false;

        //in the Big Map, check if player is available and add new owner-team to the map
        if (!leagueService.addPlayerToUserTeam(league, player, userTeam)) {
            return false;
        }

        //add the Player to the Teams player list
        userTeam.getPlayers().add(player);
        //preis wird dem team nur berechnet wenn verrechnen true ist.
        if (verrechnen) {
            userTeam.setBudget(userTeam.getBudget() - player.getPrice());
        }
        teamRepository.save(userTeam);
        return true;
    }

    @Transactional
    public boolean deletePlayerFromUserTeam(long teamId, int playerId, boolean verrechnen) {
        //get player, userTeam and the teams league from the DB
        Optional<UserTeam> team = teamRepository.findById(teamId);
        if (team.isEmpty()) return false;
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if (playerOptional.isEmpty()) return false;
        UserTeam userTeam = team.get();
        Player player = playerOptional.get();
        League league = userTeam.getLeague();
        if (league == null) return false;

        //check if the userTeam contains the player
        if (!userTeam.getPlayers().contains(player)) return false;

        //in the Big Map, check if player is available and remove owner-team from the map
        if (!leagueService.deletePlayerFromUserTeam(league, player, userTeam)) return false;

        if (!userTeam.getPlayers().remove(player)) return false;

        //verrechnen
        if (verrechnen) {
            userTeam.setBudget(userTeam.getBudget() + player.getPrice());
        }
        teamRepository.save(userTeam);
        return true;
    }


}
