package com.deepakshankar.cartracker.service;

import com.deepakshankar.cartracker.entity.Alert;
import com.deepakshankar.cartracker.entity.AlertType;
import com.deepakshankar.cartracker.entity.Reading;
import com.deepakshankar.cartracker.entity.Vehicle;

import java.util.List;

public interface AlertService {

    /**
     * implement this method to get the Alerts for a given vin number of a {@link Vehicle} object.
     *
     * @param vin the vin number of the {@link Vehicle} objects whose alerts are needed
     * @return the list of alerts of the given vehicle.
     */
    List<Alert> getAlertsForVin(final String vin);

    /**
     * implement this method to get alerts for a given vin number of a {@link Vehicle} object with desired alerts
     * priority type.
     *
     * @param vin      the vin number of the {@link Vehicle} object
     * @param priority the priority level defined in the {@link AlertType}
     * @return the list of alerts of the given vehicle with given priority.
     */
    List<Alert> getPriorityAlertsForVin(final String vin, final String priority);

    /**
     * implement this method to get a list of alerts for a given {@link Vehicle} object.
     *
     * @param vehicle the {@link Vehicle} object to get the alerts for
     * @return list of alerts for the given vehicle.
     */
    List<Alert> getAlertsForVehicle(final Vehicle vehicle);

    /**
     * implement this method to get all the alerts for all the vehicles.
     *
     * @return list of alerts of all the vehicles.
     */
    List<Alert> getAlerts();

    /**
     * implement this method to get alerts for a given {@link Vehicle} object and {@link AlertType} priority.
     *
     * @param vehicle  the {@link Vehicle object} to get the alerts for.
     * @param priority the priority level or the alerts
     * @return list of alerts.S
     */
    List<Alert> getPriorityAlertsForVehicle(Vehicle vehicle, AlertType priority);

    /**
     * implement this method to get alerts for all vehicles with a given priority level.
     *
     * @param priority the required priority for the alerts
     * @return list of alerts.
     */
    List<Alert> getPriorityAlerts(AlertType priority);

    /**
     * implement this method to get an alert with given id
     *
     * @param id the id of the alert
     * @return the alert
     */
    Alert getAlert(final String id);

    /**
     * implement this method to create a new alert
     *
     * @param alert the alert to be created.
     */
    public void create(Alert alert);

    /**
     * implement this method to create alerts for the given reading
     *
     * @param reading the reading object used to create alerts
     */
    public void createAlertForReading(Reading reading);

    public void delete(Alert alert);

    public void deleteAlertsForVehicle(final Vehicle vehicle);
}
