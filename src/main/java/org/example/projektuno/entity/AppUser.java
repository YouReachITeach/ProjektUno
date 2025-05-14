package org.example.projektuno.entity;

import jakarta.persistence.*;

@Entity
public class AppUser {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private String email;

    private String password; // Wird erstmal im Klartext gespeichert (nicht empfohlen, aber okay für den Anfang)

    // Konstruktoren
    public AppUser() {}

    public AppUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getter & Setter
    public Long getId() { return id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}

