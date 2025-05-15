package org.example.projektuno.controller;

import org.example.projektuno.entity.Player;
import org.example.projektuno.repositories.PlayerRepository;
import org.example.projektuno.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


import java.util.Collection;

@RestController
@RequestMapping("/api/player")

public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/getAllPlayers")
    public ResponseEntity<?> getAllPlayers() {
        return new ResponseEntity<>(playerService.getAllPlayers(), HttpStatus.OK);
    }

    @GetMapping("/getPlayerById/{id}")
    public ResponseEntity<?> getPlayerById(@PathVariable int id) {
        Player player = playerService.getPlayerById(id);
        if (player == null) {
            return new ResponseEntity<>("Player not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping("/createPlayer")
    public ResponseEntity<?> createPlayer(@RequestBody Player player) {
        return new ResponseEntity<>(playerService.createPlayer(player), HttpStatus.CREATED);
    }

    @PutMapping("/updatePlayer/{id}")
    public ResponseEntity<?> updatePlayer(@PathVariable int id, @RequestBody Player player) {
        Player updatedPlayer = playerService.updatePlayer(id, player);
        if (updatedPlayer != null) {
            return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Player not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletePlayer/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable int id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<?> getAvailablePlayers() {
    return new ResponseEntity<>(playerService.getPlayersNotInAnyTeam(), HttpStatus.OK);
}



}
