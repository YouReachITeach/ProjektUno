package org.example.projektuno.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Entity
public class League implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany
    private Map<Player, UserTeam> players;


    // Constructors
    public League() {
        this.players = new HashMap<>();
    }


    public League(String name, List<Player> players) {
        this.name = name;
        this.players = new HashMap<>();
        for (Player player : players) {
            this.players.put(player, null); // Assuming PLayers Owner is not set initially
        }
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


    public List<Player> getPlayersList() {
        return players.keySet().stream().toList();
    }

    public Map<Player, UserTeam> getPlayersMap() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = new HashMap<>();
        for (Player player : players) {
            this.players.put(player, null); // Assuming players owner is not set initially
        }
    }

    public void setPlayers(Map<Player, UserTeam> players) {
        this.players = players;
    }


}
