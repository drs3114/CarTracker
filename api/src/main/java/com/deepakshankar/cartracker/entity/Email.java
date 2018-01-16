package com.deepakshankar.cartracker.entity;

import javax.persistence.*;
import java.util.UUID;

/**
 * This is an email entity class used in sending emails as notifications.
 *
 * @author Deepak Shankar
 */
@Entity
@Table(name = "NOTIFICATIONS")
public class Email {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "FROM_EMAIL")
    private String fromEmail;

    @Column(name = "TO_EMAIL")
    private String toEmail;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "BODY")
    private String body;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "VIN")
    private Vehicle vehicle;

    public Email() {
        this.id = UUID.randomUUID().toString();
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
