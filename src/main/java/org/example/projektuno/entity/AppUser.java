package org.example.projektuno.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AppUser {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;


    @Column
    @OneToMany
    private List<UserTeam> userTeams = new ArrayList<>();

    private String email;

    private String password; // Wird erstmal im Klartext gespeichert (nicht empfohlen, aber okay für den Anfang)

    // Konstruktoren
    public AppUser() {
    }

    public AppUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check for reference equality
        if (o == null || getClass() != o.getClass()) return false; // Check for null and type
        AppUser appUser = (AppUser) o;
        return id == appUser.id; // Compare unique identifier
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id); // Use Integer.hashCode for int type
    }


}

