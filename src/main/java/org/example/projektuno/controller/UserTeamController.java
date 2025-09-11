package org.example.projektuno.controller;

import org.example.projektuno.entity.AppUser;
import org.example.projektuno.entity.UserTeam;
import org.example.projektuno.service.UserTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.example.projektuno.service.AppUserService;


@RestController
@RequestMapping("/api/userTeam")
public class UserTeamController {

    @Autowired
    private UserTeamService teamService;

    @Autowired
    private AppUserService userService;

    @GetMapping("/allByUserID/{userId}")
    public ResponseEntity<?> getTeamByUser(@PathVariable int userId) {
        List<UserTeam> team = teamService.getAllTeamsForUser(userId);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAllTeams() {
        List<UserTeam> teams = teamService.getAllUserTeams();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<?> getTeamById(@PathVariable int teamId) {
        Optional<UserTeam> team = teamService.getById(teamId);
        if (team.isPresent()) {
            return ResponseEntity.ok(team.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
        }
    }

    @PostMapping()
    public ResponseEntity<UserTeam> createTeam(@RequestParam String name, @RequestParam int userId) {
        AppUser user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserTeam userTeam = new UserTeam(name, user, 1000);
        UserTeam team = teamService.createUserTeam(userTeam);
        return ResponseEntity.ok(team);
    }


    @PostMapping("/{teamId}/addPlayer/{playerId}")
    public ResponseEntity<?> addPlayer(@PathVariable int teamId, @PathVariable int playerId) {
        boolean success = teamService.addPlayerToUserTeam(teamId, playerId, false);
        return success ? ResponseEntity.ok("Player added") : ResponseEntity.badRequest().body("Could not add player");
    }


    @PostMapping("/{teamId}/buyPlayer/{playerId}")
    public ResponseEntity<String> buyPlayer(@PathVariable int teamId, @PathVariable int playerId) {
        boolean success = teamService.addPlayerToUserTeam(teamId, playerId, true);
        return success ? ResponseEntity.ok("Spieler erfolgreich gekauft.")
                : ResponseEntity.badRequest().body("Kauf fehlgeschlagen (Team, Spieler oder Budgetproblem).");
    }

    @DeleteMapping("/{teamId}/removePlayer/{playerId}")
    public ResponseEntity<String> removePlayer(@PathVariable int teamId, @PathVariable int playerId) {
        boolean success = teamService.deletePlayerFromUserTeam(teamId, playerId, false);
        return success ? ResponseEntity.ok("Player removed") : ResponseEntity.badRequest().body("Could not remove player");
    }

    @DeleteMapping("/{teamId}/sellPlayer/{playerId}")
    public ResponseEntity<String> sellPlayer(@PathVariable int teamId, @PathVariable int playerId) {
        boolean success = teamService.deletePlayerFromUserTeam(teamId, playerId, true);
        return success
                ? ResponseEntity.ok("Spieler verkauft")
                : ResponseEntity.badRequest().body("Verkauf fehlgeschlagen.");
    }


}
