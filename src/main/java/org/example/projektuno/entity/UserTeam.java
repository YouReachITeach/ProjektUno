package org.example.projektuno.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class UserTeam {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToOne
    private AppUser user;

    @JsonBackReference
    @ManyToOne()
    private League league;

    private int budget = 1000;


    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Player> players = new HashSet<>();


    public UserTeam() {
    }

    public UserTeam(String name, AppUser user, int budget, League league) {
        this.name = name;
        this.user = user;
        this.budget = budget;
        this.league = league;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players.clear();
        this.players.addAll(players);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check for reference equality
        if (o == null || getClass() != o.getClass()) return false; // Check for null and type
        UserTeam userTeam = (UserTeam) o;
        if (id == 0 && userTeam.id == 0) return false; //check for unpersisted entities
        return id == userTeam.id; // Compare IDs
    }

    @Override
    public int hashCode() {
        return id;
    }
}
