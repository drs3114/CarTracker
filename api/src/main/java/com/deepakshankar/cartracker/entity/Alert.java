package com.deepakshankar.cartracker.entity;

import javax.persistence.*;
import java.util.UUID;

/**
 * This is an entity class that is used to create and store alerts for a vehicle when some specific events occur.
 *
 * @author Deepak Shankar
 */
@Entity
@Table(name = "ALERTS")
public class Alert {

    @Id
    @Column(name = "ALERT_ID")
    private String id;

    @ManyToOne()
    private Vehicle vehicle;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "TYPE")
    private AlertType type;

    public Alert() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AlertType getType() {
        return type;
    }

    public void setType(AlertType type) {
        this.type = type;
    }
}
