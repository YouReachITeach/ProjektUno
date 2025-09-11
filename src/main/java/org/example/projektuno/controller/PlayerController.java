package org.example.projektuno.controller;

import org.example.projektuno.entity.Player;
import org.example.projektuno.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/player")

public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping()
    public ResponseEntity<?> getAllPlayers() {
        return new ResponseEntity<>(playerService.getAllPlayers(), HttpStatus.OK);
    }

    @GetMapping("/getPlayerType/{id}")
    public ResponseEntity<?> getPlayerType(@PathVariable int id) {
        String playerType = playerService.getPlayerType(id);
        if (playerType == null) {
            return new ResponseEntity<>("Player not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(playerType, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayerById(@PathVariable int id) {
        Player player = playerService.getPlayerById(id);
        if (player == null) {
            return new ResponseEntity<>("Player not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createPlayer(@RequestBody Player player) {
        return new ResponseEntity<>(playerService.createPlayer(player), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlayer(@PathVariable int id, @RequestBody Player player) {
        Player updatedPlayer = playerService.updatePlayer(id, player);
        if (updatedPlayer != null) {
            return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Player not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable int id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }

}




