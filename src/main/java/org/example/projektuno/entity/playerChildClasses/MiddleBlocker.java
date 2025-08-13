package org.example.projektuno.entity.playerChildClasses;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import org.example.projektuno.entity.Player;

@Entity
@DiscriminatorValue("MIDDLE_BLOCKER")
public class MiddleBlocker extends Player {
    private int blocks;         // Successful blocks
    private int spikes;         // Successful attacks
    private int serviceAces;    // Direct points from serves
    private int blockErrors;    // Errors in blocking

    public MiddleBlocker() {
        super();
    }

    public MiddleBlocker(String name, int price, int blocks, int spikes, int serviceAces, int blockErrors) {
        super(name, price);
        this.blocks = blocks;
        this.spikes = spikes;
        this.serviceAces = serviceAces;
        this.blockErrors = blockErrors;
    }

    // Getters and Setters
    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getSpikes() {
        return spikes;
    }

    public void setSpikes(int spikes) {
        this.spikes = spikes;
    }

    public int getServiceAces() {
        return serviceAces;
    }

    public void setServiceAces(int serviceAces) {
        this.serviceAces = serviceAces;
    }

    public int getBlockErrors() {
        return blockErrors;
    }

    public void setBlockErrors(int blockErrors) {
        this.blockErrors = blockErrors;
    }
}