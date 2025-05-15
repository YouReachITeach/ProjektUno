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
                player.getUserTeams().add(team); // Spieler wird dem Team hinzugefügt
                playerRepository.save(player); // ⚠️ reicht, da UserTeam über Player referenziert ist
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
                player.getUserTeams().remove(team); // Spieler wird dem Team entfernt
                playerRepository.save(player); // ⚠️ reicht, da UserTeam über Player referenziert ist
                teamRepository.save(team);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean buyPlayer(Long teamId, int playerId) {
        Optional<UserTeam> teamOpt = teamRepository.findById(teamId);
        Optional<Player> playerOpt = playerRepository.findById(playerId);

        if (teamOpt.isEmpty() || playerOpt.isEmpty()) {
            System.out.println("❌ Team oder Spieler nicht gefunden.");
            return false;
        }

        UserTeam team = teamOpt.get();
        Player player = playerOpt.get();
        AppUser user = team.getUser();

        int price = player.getPrice();
        int budget = user.getBudget();

        System.out.println("🛒 Kaufversuch: Spieler '" + player.getName() + "', Preis: " + price + ", Budget: " + budget);

        if (budget >= price && !team.getPlayers().contains(player)) {
            user.setBudget(budget - price);
            team.getPlayers().add(player);
            player.getUserTeams().add(team); // Spieler wird dem Team hinzugefügt(änderung)
            playerRepository.save(player); // ⚠️ reicht, da UserTeam über Player referenziert ist
            teamRepository.save(team);
            System.out.println("✅ Spieler gekauft. Neues Budget: " + user.getBudget());
            return true;
        }

        System.out.println("❌ Kauf abgelehnt (nicht genug Budget oder Spieler bereits im Team).");
        return false;
    }


    @Transactional
    public boolean sellPlayer(Long teamId, int playerId) {
        Optional<UserTeam> teamOpt = teamRepository.findById(teamId);
        Optional<Player> playerOpt = playerRepository.findById(playerId);

        if (teamOpt.isPresent() && playerOpt.isPresent()) {
            UserTeam team = teamOpt.get();
            Player player = playerOpt.get();
            AppUser user = team.getUser();

            if (team.getPlayers().contains(player)) {
                team.getPlayers().remove(player);
                player.getUserTeams().remove(team); // Spieler wird dem Team entfernt
                playerRepository.save(player); // ⚠️ reicht, da UserTeam über Player referenziert ist
                user.setBudget(user.getBudget() + player.getPrice());
                teamRepository.save(team); // ⚠️ reicht, da AppUser über Team referenziert ist
                return true;
            }
        }
        return false;
    }


}
