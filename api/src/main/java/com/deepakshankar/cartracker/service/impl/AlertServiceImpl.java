package com.deepakshankar.cartracker.service.impl;

import com.deepakshankar.cartracker.dao.AlertDao;
import com.deepakshankar.cartracker.dao.VehicleDao;
import com.deepakshankar.cartracker.entity.Alert;
import com.deepakshankar.cartracker.entity.AlertType;
import com.deepakshankar.cartracker.entity.Vehicle;
import com.deepakshankar.cartracker.exceptions.VehicleNotFoundException;
import com.deepakshankar.cartracker.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Alert> getPriorityAlertsForVin(String vin, String priority) {
        Vehicle vehicle = vehicleDao.findOne(vin);
        if (priority != null && priority != "") {
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
    public Alert getAlert(String id) {
        return alertDao.findOne(id);
    }
}
