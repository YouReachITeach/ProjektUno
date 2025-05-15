package org.example.projektuno.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Column
    private int price;


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

    // Getter for JSON output (used in frontend)
    @JsonProperty("playerType")
    public String getPlayerType() {
        return this.getClass().getSimpleName().toUpperCase();
    }

    // Standard Getters and Setters
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

    public int getPrice() {
    return price;
    }

    public void setPrice(int price) {
    this.price = price;
    }

    

   
}



