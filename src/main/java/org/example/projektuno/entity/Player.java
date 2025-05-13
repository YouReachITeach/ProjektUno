package org.example.projektuno.entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "playerType")
public class Player implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany(mappedBy = "players")
    private List<League> leagues;


    // Constructors
    public Player() {
        this.leagues = new ArrayList<>();
    }

    public Player(String name) {
        this.name = name;
        this.leagues = new ArrayList<>();
    }

    public Player(String name, List<League> leagues) {
        this.name = name;
        this.leagues = leagues;
    }


    // Add and Remove a league to the player
    public boolean addLeague(League league) {
        if (league != null) {
            this.leagues.add(league);
            league.getPlayers().add(this);
            return true;
        }
        return false;
    }

    public boolean removeLeague(League league) {
        if (league == null || !this.leagues.contains(league)) {
            return false;
        }
        this.leagues.remove(league);
        league.getPlayers().remove(this);
        return true;
    }


    //getters and setters

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }

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
}
