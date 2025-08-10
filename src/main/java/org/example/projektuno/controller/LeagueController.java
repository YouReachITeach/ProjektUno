package org.example.projektuno.controller;

import org.example.projektuno.entity.League;
import org.example.projektuno.entity.Player;
import org.example.projektuno.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/api/league")

public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @GetMapping("/getAllLeagues")
    public ResponseEntity<?> getAllLeagues() {
        return new ResponseEntity<>(leagueService.getAllLeagues(), HttpStatus.OK);
    }

    @GetMapping("/getLeagueById/{id}")
    public ResponseEntity<?> getLeagueById(@PathVariable int id) {
        League league = leagueService.getLeagueById(id);
        if (league == null) {
            return new ResponseEntity<>("League not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(league, HttpStatus.OK);
    }

    @GetMapping("/getAllFreePlayersByLeagueId/{id}")
    public ResponseEntity<?> getAllFreePLayersByLeagueId(@PathVariable int id) {
        Set<Player> leagues = leagueService.getFreePlayers(id);
        if (leagues == null || leagues.isEmpty()) {
            return new ResponseEntity<>("No free players found in this league", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(leagues, HttpStatus.OK);
    }

    @PostMapping("/createLeague/{name}")
    public ResponseEntity<?> createLeague(@PathVariable String name) {
        return new ResponseEntity<>(leagueService.createLeague(name).getId(), HttpStatus.CREATED);
        //returns the ID of the created League for further referencing in Frontend
    }

    @PutMapping("/updateLeague/{id}")
    public ResponseEntity<?> updateLeague(@PathVariable int id, @RequestBody League league) {
        League updatedLeague = leagueService.updateLeague(id, league);
        if (updatedLeague != null) {
            return new ResponseEntity<>(updatedLeague, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("League not found", HttpStatus.NOT_FOUND);
        }
    }

    //@PutMapping("/putPlayerListInLeague")

    @DeleteMapping("/deleteLeague/{id}")
    public ResponseEntity<?> deleteLeague(@PathVariable int id) {
        leagueService.deleteLeague(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
