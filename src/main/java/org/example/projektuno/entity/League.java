package org.example.projektuno.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import org.springframework.data.repository.CrudRepositoryExtensionsKt;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    private Set<Player> freePlayers;

    @JsonManagedReference
    @OneToMany(mappedBy = "league")
    private Set<UserTeam> userTeams;

    // Constructors
    public League() {
        this.freePlayers = new HashSet<>();
        this.userTeams = new HashSet<>();
    }


    public League(String name, Set<Player> players) {
        this.name = name;
        this.freePlayers = new HashSet<>();
        this.freePlayers.addAll(players);
        this.userTeams = new HashSet<>();
    }


    //getters and setters
    public Set<UserTeam> getUserTeams() {
        return userTeams;
    }

    public void setUserTeams(Set<UserTeam> userTeams) {
        this.userTeams.clear();
        this.userTeams.addAll(userTeams);
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


    public List<Player> getFreePlayersList() {
        return freePlayers.stream().toList();
    }

    public Set<Player> getFreePlayersSet() {
        return freePlayers;
    }

    public void setFreePlayers(Set<Player> freePlayers) {
        this.freePlayers.clear();
        this.freePlayers.addAll(freePlayers);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check for reference equality
        if (o == null || getClass() != o.getClass()) return false; // Check for null and type
        League league = (League) o;
        if (id == 0 && league.id == 0) return false;
        return id == league.id; // Compare unique identifier
    }

    @Override
    public int hashCode() {
        return id; // Use Integer.hashCode for int type
    }


}
