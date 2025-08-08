package org.example.projektuno.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


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

    @Column
    private Integer points = 0;


    // Constructors
    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    // Getter for JSON output (used in frontend)
    @JsonProperty("playerType")
    public String getPlayerType() {
        return this.getClass().getSimpleName().toUpperCase();
    }

    // Standard Getters and Setters


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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}


