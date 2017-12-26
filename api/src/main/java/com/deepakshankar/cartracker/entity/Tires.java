package com.deepakshankar.cartracker.entity;

import javax.persistence.*;

@Entity
@Table(name = "TIRES")
public class Tires {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TIRE_ID")
    private long tireId;

    @Column(name = "FRONT_LEFT")
    private int frontLeft;

    @Column(name = "FRONT_RIGHT")
    private int frontRight;

    @Column(name = "REAR_LEFT")

    private int rearLeft;

    @Column(name = "REAR_RIGHT")
    private int rearRight;

    public int getFrontLeft() {
        return frontLeft;
    }

    public void setFrontLeft(int frontLeft) {
        this.frontLeft = frontLeft;
    }

    public int getFrontRight() {
        return frontRight;
    }

    public void setFrontRight(int frontRight) {
        this.frontRight = frontRight;
    }

    public int getRearLeft() {
        return rearLeft;
    }

    public void setRearLeft(int rearLeft) {
        this.rearLeft = rearLeft;
    }

    public int getRearRight() {
        return rearRight;
    }

    public void setRearRight(int rearRight) {
        this.rearRight = rearRight;
    }
}
