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

    @OneToOne
    private AppUser user;

   @ManyToMany(fetch = FetchType.EAGER)
    private List<Player> players = new ArrayList<>();


    public UserTeam() {}

    public UserTeam(String name, AppUser user) {
        this.name = name;
        this.user = user;
    }

    // Getter & Setter
    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public AppUser getUser() { return user; }

    public void setUser(AppUser user) { this.user = user; }

    public List<Player> getPlayers() { return players; }

    public void setPlayers(List<Player> players) { this.players = players; }
}