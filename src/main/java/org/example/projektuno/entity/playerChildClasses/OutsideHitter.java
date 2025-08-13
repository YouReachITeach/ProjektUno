package org.example.projektuno.entity.playerChildClasses;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import org.example.projektuno.entity.Player;

@Entity
@DiscriminatorValue("OUTSIDE_HITTER")
public class OutsideHitter extends Player {
    private int spikes;         // Successful attacks
    private int blocks;         // Successful blocks
    private int serviceAces;    // Direct points from serves
    private int attackErrors;   // Errors in attack

    public OutsideHitter() {
        super();
    }

    public OutsideHitter(String name, int price, int spikes, int blocks, int serviceAces, int attackErrors) {
        super(name, price);
        this.spikes = spikes;
        this.blocks = blocks;
        this.serviceAces = serviceAces;
        this.attackErrors = attackErrors;
    }

    // Getters and Setters
    public int getSpikes() {
        return spikes;
    }

    public void setSpikes(int spikes) {
        this.spikes = spikes;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getServiceAces() {
        return serviceAces;
    }

    public void setServiceAces(int serviceAces) {
        this.serviceAces = serviceAces;
    }

    public int getAttackErrors() {
        return attackErrors;
    }

    public void setAttackErrors(int attackErrors) {
        this.attackErrors = attackErrors;
    }
}