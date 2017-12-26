package com.deepakshankar.cartracker.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "READINGS")
public class Reading {

    @Id
    @Column(name="READING_ID")
    private String id;

    @Column(name = "VIN")
    private String vin;

    @Column(name = "LATITUDE")
    private double latitude;

    @Column(name = "LONGITUDE")
    private double longitude;

    @Column(name = "TIMESTAMP", nullable = false)
    private Date timestamp;

    @Column(name = "FUEL_VOLUME")
    private float fuelVolume;

    @Column(name = "SPEED")
    private int speed;

    @Column(name = "ENGINE_HP")
    private int engineHp;

    @Column(name = "CHK_ENG_LIGHT_ON")
    private boolean checkEngineLightOn;

    @Column(name = "ENG_CLNT_LOW")
    private boolean engineCoolantLow;

    @Column(name = "CRUISE_CTRL")
    private boolean cruiseControlOn;

    @Column(name = "ENG_RPM")
    private int engineRpm;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="TIRE_ID")
    private Tires tires;

    public Reading(){
        this.id = UUID.randomUUID().toString();
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public float getFuelVolume() {
        return fuelVolume;
    }

    public void setFuelVolume(float fuelVolume) {
        this.fuelVolume = fuelVolume;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getEngineHp() {
        return engineHp;
    }

    public void setEngineHp(int engineHp) {
        this.engineHp = engineHp;
    }

    public boolean isCheckEngineLightOn() {
        return checkEngineLightOn;
    }

    public void setCheckEngineLightOn(boolean checkEngineLightOn) {
        this.checkEngineLightOn = checkEngineLightOn;
    }

    public boolean isEngineCoolantLow() {
        return engineCoolantLow;
    }

    public void setEngineCoolantLow(boolean engineCoolantLow) {
        this.engineCoolantLow = engineCoolantLow;
    }

    public boolean isCruiseControlOn() {
        return cruiseControlOn;
    }

    public void setCruiseControlOn(boolean cruiseControlOn) {
        this.cruiseControlOn = cruiseControlOn;
    }

    public int getEngineRpm() {
        return engineRpm;
    }

    public void setEngineRpm(int engineRpm) {
        this.engineRpm = engineRpm;
    }

    public Tires getTires() {
        return tires;
    }

    public void setTires(Tires tires) {
        this.tires = tires;
    }
}
