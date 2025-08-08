package org.example.projektuno.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserTeam {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private AppUser user;

    @OneToOne(fetch = FetchType.EAGER)
    private League league;

    private int budget = 1000;


    @ManyToMany(fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();


    public UserTeam() {
    }

    public UserTeam(String name, AppUser user) {
        this.name = name;
        this.user = user;
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
