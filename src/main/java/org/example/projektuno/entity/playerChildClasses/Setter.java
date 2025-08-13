package org.example.projektuno.entity.playerChildClasses;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import org.example.projektuno.entity.Player;

@Entity
@DiscriminatorValue("SETTER")
public class Setter extends Player {
    private int assists;        // Successful sets leading to points
    private int serviceAces;    // Direct points from serves
    private int blocks;         // Successful blocks
    private int settingErrors;  // Errors in setting

    public Setter() {
        super();
    }

    public Setter(String name, int price, int age, int assists, int serviceAces, int blocks, int settingErrors) {
        super(name, price);
        this.assists = assists;
        this.serviceAces = serviceAces;
        this.blocks = blocks;
        this.settingErrors = settingErrors;
    }

    // Getters and Setters
    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getServiceAces() {
        return serviceAces;
    }

    public void setServiceAces(int serviceAces) {
        this.serviceAces = serviceAces;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getSettingErrors() {
        return settingErrors;
    }

    public void setSettingErrors(int settingErrors) {
        this.settingErrors = settingErrors;
    }
}