package org.example.projektuno.controller;

import org.example.projektuno.entity.League;
import org.example.projektuno.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/createLeague")
    public ResponseEntity<?> createLeague(@RequestBody League league) {
        return new ResponseEntity<>(leagueService.createLeague(league), HttpStatus.CREATED);
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

    @DeleteMapping("/deleteLeague/{id}")
    public ResponseEntity<?> deleteLeague(@PathVariable int id) {
        leagueService.deleteLeague(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
