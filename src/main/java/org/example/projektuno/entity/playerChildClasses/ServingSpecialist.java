package org.example.projektuno.entity.playerChildClasses;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import org.example.projektuno.entity.Player;

@Entity
@DiscriminatorValue("SERVING_SPECIALIST")
public class ServingSpecialist extends Player {
    private int serviceAces;    // Direct points from serves
    private int serviceErrors;  // Errors in service
    private int servicePoints;  // Total points from service

    public ServingSpecialist() {
        super();
    }

    public ServingSpecialist(String name, int price, int serviceAces, int serviceErrors, int servicePoints) {
        super(name, price);
        this.serviceAces = serviceAces;
        this.serviceErrors = serviceErrors;
        this.servicePoints = servicePoints;
    }

    // Getters and Setters
    public int getServiceAces() {
        return serviceAces;
    }

    public void setServiceAces(int serviceAces) {
        this.serviceAces = serviceAces;
    }

    public int getServiceErrors() {
        return serviceErrors;
    }

    public void setServiceErrors(int serviceErrors) {
        this.serviceErrors = serviceErrors;
    }

    public int getServicePoints() {
        return servicePoints;
    }

    public void setServicePoints(int servicePoints) {
        this.servicePoints = servicePoints;
    }
}