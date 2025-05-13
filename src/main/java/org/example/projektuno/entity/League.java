package org.example.projektuno.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class League implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany
    private List<Player> players;


    // Constructors
    public League() {
        this.players = new ArrayList<>();
    }

    public League(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    public League(String name, List<Player> players) {
        this.name = name;
        this.players = players;
    }


    // Add and Remove a player to the league (ONLY USE THIS IF NOT ADDED/REMOVED BY PLAYER!!!)
    private boolean addPlayer(Player player) {
        if (player != null && !this.players.contains(player)) {
            this.players.add(player);
            player.getLeagues().add(this);
            return true;
        }
        return false;
    }

    private boolean removePlayer(Player player) {
        if (player == null || !this.players.contains(player)) {
            return false;
        }
        this.players.remove(player);
        player.getLeagues().remove(this);
        return true;
    }


    //getters and setters

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}
