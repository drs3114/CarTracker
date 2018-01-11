package com.deepakshankar.cartracker.service.impl;

import com.deepakshankar.cartracker.dao.AlertDao;
import com.deepakshankar.cartracker.dao.VehicleDao;
import com.deepakshankar.cartracker.entity.*;
import com.deepakshankar.cartracker.exceptions.VehicleNotFoundException;
import com.deepakshankar.cartracker.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private AlertDao alertDao;

    @Autowired
    private VehicleDao vehicleDao;

    /**
     * this method provides the implementation to get alerts for a vehicle with a given vin number
     *
     * @param vin the vin number of the {@link Vehicle} objects whose alerts are needed
     * @return list of alerts for the vehicle
     */
    @Override
    @Transactional(readOnly = true)
    public List<Alert> getAlertsForVin(final String vin) {
        Vehicle vehicle = vehicleDao.findOne(vin);

        return getAlertsForVehicle(vehicle);


    }

    /**
     * this method provides the implementation to get alerts for a vehicle with a given vin number and priority level.
     * the priority levels are defined in the {@link AlertType} object with the values <code>HIGH, MEDIUM and LOW</code>
     *
     * @param vin      the vin number of the {@link Vehicle} object
     * @param priority the priority level defined in the {@link AlertType}
     * @return the list of alerts
     */
    @Override
    @Transactional(readOnly = true)
    public List<Alert> getPriorityAlertsForVin(String vin, String priority) {
        Vehicle vehicle = vehicleDao.findOne(vin);
        if (!Objects.equals(priority, "")) {
            AlertType type = AlertType.valueOf(priority.toUpperCase());
            return getPriorityAlertsForVehicle(vehicle, type);
        }
        return getAlertsForVehicle(vehicle);
    }

    /**
     * this method provides the implementation to get alerts for a vehicle with a given {@link Vehicle} object.
     *
     * @param vehicle the {@link Vehicle} object to get the alerts for
     * @return the list of alerts.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Alert> getAlertsForVehicle(final Vehicle vehicle) {
        if (vehicle == null)
            throw new VehicleNotFoundException();

        return alertDao.findAlertByVehicle(vehicle);
    }

    /**
     * this method provides the implementation to get alerts for all vehicles.
     *
     * @return the list of alerts.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Alert> getAlerts() {
        return alertDao.findAll();
    }

    /**
     * this method provides the implementation to get the alerts for a given {@link Vehicle} object and
     * {@link AlertType} object
     *
     * @param vehicle the {@link Vehicle object} to get the alerts for.
     * @param type    the required priority level
     * @return the list of alerts
     */
    @Override
    @Transactional(readOnly = true)
    public List<Alert> getPriorityAlertsForVehicle(Vehicle vehicle, AlertType type) {
        if (vehicle == null)
            throw new VehicleNotFoundException();

        return alertDao.findAlertByVehicleAndAndType(vehicle, type);
    }

    /**
     * this method provides the implementation to get the alerts for all the vehicles for a given priority level.
     *
     * @param priority the required priority for the alerts.
     * @return the list of alerts.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Alert> getPriorityAlerts(AlertType priority) {
        return alertDao.findAlertByType(priority);
    }

    /**
     * this method provides the implementation to get the alerts for all a given id of the alert.
     *
     * @param id the id of the alert.
     * @return the alert.
     */
    @Override
    @Transactional(readOnly = true)
    public Alert getAlert(String id) {
        return alertDao.findOne(id);
    }


    /**
     * this method is used to persist a new {@link Alert} object into the database
     *
     * @param alert the alert to be created.
     */
    @Override
    @Transactional
    public void create(Alert alert) {

        alertDao.save(alert);

    }

    /**
     * this method is used to persist {@link Alert} objects based on the {@link Reading} object.
     *
     * @param reading the reading object used to create alerts
     */
    @Override
    public void createAlertForReading(Reading reading) {

        Vehicle vehicle = vehicleDao.findOne(reading.getVin());
        if (vehicle != null) {
            if (reading.getEngineRpm() > vehicle.getRedlineRpm()) {

                String message = "Engine RPM is: " + reading.getEngineRpm() + ". The red line RPM is: " +
                        vehicle.getRedlineRpm() + ".";
                createAlert(vehicle, message, AlertType.HIGH);
            }
            if (reading.getFuelVolume() < vehicle.getMaxFuelVolume() / 10) {


                String message = "Fuel volume is getting low. Please consider re-fuelling";
                createAlert(vehicle, message, AlertType.MEDIUM);
            }
            if (isAirPressureOutOfRange(reading)) {

                String message = "Check tire air pressures!";
                createAlert(vehicle, message, AlertType.LOW);
            }
            if (reading.isEngineCoolantLow() || reading.isCheckEngineLightOn()) {

                String message = "Check engine! Refill coolant or service engine!";
                createAlert(vehicle, message, AlertType.LOW);
            }

        }

    }

    @Override
    @Transactional
    public void delete(final Alert alert) {
        alertDao.delete(alert);
    }

    @Override
    @Transactional
    public void deleteAlertsForVehicle(final Vehicle vehicle) {
        getAlertsForVehicle(vehicle).forEach(alert -> {
            delete(alert);
        });
    }

    /**
     * This is a utility method used to check tire pressure to create alerts.
     *
     * @param reading the reading object which has the values
     * @return true is air pressure is not in the range defined in {@link Tires}
     */
    private boolean isAirPressureOutOfRange(Reading reading) {
        return reading.getTires().getFrontLeft() < Tires.MIN_TIRE_PRESSURE ||
                reading.getTires().getFrontLeft() > Tires.MAX_TIRE_PRESSURE ||
                reading.getTires().getFrontRight() < Tires.MIN_TIRE_PRESSURE ||
                reading.getTires().getFrontRight() > Tires.MAX_TIRE_PRESSURE ||
                reading.getTires().getRearLeft() < Tires.MIN_TIRE_PRESSURE ||
                reading.getTires().getRearLeft() > Tires.MAX_TIRE_PRESSURE ||
                reading.getTires().getRearRight() < Tires.MIN_TIRE_PRESSURE ||
                reading.getTires().getRearRight() > Tires.MAX_TIRE_PRESSURE;
    }

    /**
     * this is a utility function that will create a new {@link Alert} object based on the sent parameters
     *
     * @param vehicle  the vehicle for which the alert has to be created.
     * @param message  the message of the alert.
     * @param priority the priority of the alert.
     */
    private void createAlert(Vehicle vehicle, String message, AlertType priority) {
        Alert alert = new Alert();
        alert.setVehicle(vehicle);
        alert.setMessage(message);
        alert.setType(priority);
        create(alert);
    }
}
