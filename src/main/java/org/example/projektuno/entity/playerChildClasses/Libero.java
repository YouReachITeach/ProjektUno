package org.example.projektuno.entity.playerChildClasses;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import org.example.projektuno.entity.Player;

@Entity
@DiscriminatorValue("LIBERO")
public class Libero extends Player {
    private int digs;           // Successful defensive plays
    private int receptions;     // Successful first contacts
    private int serviceAces;    // Direct points from serves
    private int passingErrors;  // Errors in reception

    public Libero() {
        super();
    }

    public Libero(String name, int price, int digs, int receptions, int serviceAces, int passingErrors) {
        super(name, price);
        this.digs = digs;
        this.receptions = receptions;
        this.serviceAces = serviceAces;
        this.passingErrors = passingErrors;
    }

    // Getters and Setters
    public int getDigs() {
        return digs;
    }

    public void setDigs(int digs) {
        this.digs = digs;
    }

    public int getReceptions() {
        return receptions;
    }

    public void setReceptions(int receptions) {
        this.receptions = receptions;
    }

    public int getServiceAces() {
        return serviceAces;
    }

    public void setServiceAces(int serviceAces) {
        this.serviceAces = serviceAces;
    }

    public int getPassingErrors() {
        return passingErrors;
    }

    public void setPassingErrors(int passingErrors) {
        this.passingErrors = passingErrors;
    }
}