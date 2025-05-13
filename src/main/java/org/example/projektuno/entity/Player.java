package org.example.projektuno.entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "playerType")
public class Player implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    public Player() {
    }

    public Player(String name) {
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
