package com.deepakshankar.cartracker.entity;

public class Alert {
    private Vehicle vin;
    private String message;
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
