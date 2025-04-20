package org.example.projektuno.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@Entity
public class Player implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    public Player() {
    }

    private Player(String name) {
        this.name = name;
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
}
