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
    public ResponseEntity<?> getTeamByUser(@PathVariable Long userId) {
        List<UserTeam> team = teamService.getAllTeamsForUser(userId);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @PostMapping("/createTeam")
    public ResponseEntity<UserTeam> createTeam(@RequestParam String name, @RequestParam Long userId) {
        AppUser user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserTeam userTeam = new UserTeam(name, user, 1000);
        UserTeam team = teamService.createTeamForUser(userTeam);
        return ResponseEntity.ok(team);
    }


    @PostMapping("/{teamId}/addPlayer/{playerId}")
    public ResponseEntity<?> addPlayer(@PathVariable Long teamId, @PathVariable int playerId) {
        boolean success = teamService.addPlayerToUserTeam(teamId, playerId, false);
        return success ? ResponseEntity.ok("Player added") : ResponseEntity.badRequest().body("Could not add player");
    }


    @PostMapping("/{teamId}/buyPlayer/{playerId}")
    public ResponseEntity<String> buyPlayer(@PathVariable Long teamId, @PathVariable int playerId) {
        boolean success = teamService.addPlayerToUserTeam(teamId, playerId, true);
        return success ? ResponseEntity.ok("Spieler erfolgreich gekauft.")
                : ResponseEntity.badRequest().body("Kauf fehlgeschlagen (Team, Spieler oder Budgetproblem).");
    }

    @DeleteMapping("/{teamId}/removePlayer/{playerId}")
    public ResponseEntity<String> removePlayer(@PathVariable Long teamId, @PathVariable int playerId) {
        boolean success = teamService.deletePlayerFromUserTeam(teamId, playerId, false);
        return success ? ResponseEntity.ok("Player removed") : ResponseEntity.badRequest().body("Could not remove player");
    }

    @DeleteMapping("/{teamId}/sellPlayer/{playerId}")
    public ResponseEntity<String> sellPlayer(@PathVariable Long teamId, @PathVariable int playerId) {
        boolean success = teamService.deletePlayerFromUserTeam(teamId, playerId, true);
        return success
                ? ResponseEntity.ok("Spieler verkauft")
                : ResponseEntity.badRequest().body("Verkauf fehlgeschlagen.");
    }


}
