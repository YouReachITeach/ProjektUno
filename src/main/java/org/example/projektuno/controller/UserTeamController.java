package org.example.projektuno.controller;

import org.example.projektuno.entity.AppUser;
import org.example.projektuno.entity.UserTeam;
import org.example.projektuno.service.UserTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import org.example.projektuno.service.AppUserService;


@RestController
@RequestMapping("/api/userTeam")
public class UserTeamController {

    @Autowired
    private UserTeamService teamService;

    @Autowired
    private AppUserService userService;

    @GetMapping("/byUserID/{userId}")
    public ResponseEntity<?> getTeamByUser(@PathVariable Long userId) {
        Optional<UserTeam> team = teamService.getTeamByUserId(userId);
        return team.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/createTeam")
    public ResponseEntity<UserTeam> createTeam(@RequestParam String name, @RequestParam Long userId) {
        AppUser user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserTeam team = teamService.createTeamForUser(name, user);
        return ResponseEntity.ok(team);
    }


    @PostMapping("/{teamId}/addPlayer/{playerId}")
    public ResponseEntity<String> addPlayer(@PathVariable Long teamId, @PathVariable int playerId) {
        boolean success = teamService.addPlayerToTeam(teamId, playerId);
        return success ? ResponseEntity.ok("Player added") : ResponseEntity.badRequest().body("Could not add player");
    }

    @DeleteMapping("/{teamId}/removePlayer/{playerId}")
    public ResponseEntity<String> removePlayer(@PathVariable Long teamId, @PathVariable int playerId) {
        boolean success = teamService.removePlayerFromTeam(teamId, playerId);
        return success ? ResponseEntity.ok("Player removed") : ResponseEntity.badRequest().body("Could not remove player");
    }

    @PostMapping("/{teamId}/buyPlayer/{playerId}")
    public ResponseEntity<String> buyPlayer(@PathVariable Long teamId, @PathVariable int playerId) {
    boolean success = teamService.buyPlayer(teamId, playerId);
    return success ? ResponseEntity.ok("Spieler erfolgreich gekauft.")
                   : ResponseEntity.badRequest().body("Kauf fehlgeschlagen (Team, Spieler oder Budgetproblem).");
}

    @DeleteMapping("/{teamId}/sellPlayer/{playerId}")
public ResponseEntity<String> sellPlayer(@PathVariable Long teamId, @PathVariable int playerId) {
    boolean success = teamService.sellPlayer(teamId, playerId);
    return success
        ? ResponseEntity.ok("Spieler verkauft")
        : ResponseEntity.badRequest().body("Verkauf fehlgeschlagen.");
}


}
