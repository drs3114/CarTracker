package com.deepakshankar.cartracker.entity;

import javax.persistence.*;

@Entity
@Table(name = "ALERTS")
public class Alert {

    @Id
    @Column(name = "ALERT_ID")
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="VIN")
    private Vehicle vin;

    @Column(name="MESSAGE")
    private String message;

    @Column(name = "TYPE")
    private AlertType type;

    public Vehicle getVin() {
        return vin;
    }

    public void setVin(Vehicle vin) {
        this.vin = vin;
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
