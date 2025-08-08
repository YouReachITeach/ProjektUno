package org.example.projektuno.service;

import jakarta.transaction.Transactional;
import org.example.projektuno.entity.AppUser;
import org.example.projektuno.entity.League;
import org.example.projektuno.entity.Player;
import org.example.projektuno.entity.UserTeam;
import org.example.projektuno.repositories.LeagueRepository;
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

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private LeagueService leagueService;


    // Ein neues Team für einen User anlegen
    public UserTeam createTeamForUser(String teamName, AppUser user) {
        UserTeam team = new UserTeam(teamName, user);
        return teamRepository.save(team);
    }

    @Transactional
    public boolean addPlayerToUserTeam(Long teamId, int playerId) {
        //get player, userTeam and the teams league from the DB
        Optional<UserTeam> team = teamRepository.findByIdWithLeague(teamId);
        if (team.isEmpty()) return false;
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if (playerOptional.isEmpty()) return false;
        UserTeam userTeam = team.get();
        Player player = playerOptional.get();
        League league = userTeam.getLeague(); // Now safe to access - league is already loaded

        //if team already owns this player, abort
        if (userTeam.getPlayers().contains(player)) return false;

        //in the Big Map, check if player is available and add new owner-team to the map
        if (!leagueService.addPlayerToUserTeam(league, player, userTeam)) {
            return false;
        }

        //add the Player to the Teams player list
        userTeam.getPlayers().add(player);
        teamRepository.save(userTeam);
        return true;
    }

    @Transactional
    public boolean buyPlayerToUserTeam(Long teamId, int playerId) {
        //get player, userTeam and the teams league from the DB
        Optional<UserTeam> team = teamRepository.findByIdWithLeague(teamId);
        if (team.isEmpty()) return false;
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if (playerOptional.isEmpty()) return false;
        UserTeam userTeam = team.get();
        Player player = playerOptional.get();
        if (userTeam.getBudget() < player.getPrice()) return false;
        League league = userTeam.getLeague(); // Now safe to access - league is already loaded

        //if team already owns this player, abort
        if (userTeam.getPlayers().contains(player)) return false;

        //in the Big Map, check if player is available and add new owner-team to the map
        if (!leagueService.addPlayerToUserTeam(league, player, userTeam)) {
            return false;
        }

        //add the Player to the Teams player list
        userTeam.getPlayers().add(player);
        userTeam.setBudget(userTeam.getBudget() - player.getPrice());
        teamRepository.save(userTeam);
        return true;
    }


}
