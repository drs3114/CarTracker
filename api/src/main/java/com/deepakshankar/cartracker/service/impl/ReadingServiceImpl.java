package com.deepakshankar.cartracker.service.impl;

import com.deepakshankar.cartracker.dao.AlertDao;
import com.deepakshankar.cartracker.dao.ReadingDao;
import com.deepakshankar.cartracker.dao.VehicleDao;
import com.deepakshankar.cartracker.entity.Alert;
import com.deepakshankar.cartracker.entity.AlertType;
import com.deepakshankar.cartracker.entity.Reading;
import com.deepakshankar.cartracker.entity.Vehicle;
import com.deepakshankar.cartracker.exceptions.BadRequestException;
import com.deepakshankar.cartracker.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is a service implementation of {@link ReadingService} that provides the necessary business logic to handle
 * all the services that are needed to handle {@link Reading} objects.
 *
 * @author Deepak Shankar
 */
@Service
public class ReadingServiceImpl implements ReadingService {

    @Autowired
    private ReadingDao readingDao;

    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private AlertDao alertDao;

    /**
     * This provides the implementation to create a new {@link Reading} record and also create {@link Alert}s based
     * on the conditions defined below.
     * <code>
     * Alert: engineRpm > readlineRpm, Priority: HIGH
     * Alert: fuelVolume < 10% of maxFuelVolume, Priority: MEDIUM
     * Alert: tire pressure of any tire < 32psi || > 36psi , Priority: LOW
     * Alert: engineCoolantLow = true || checkEngineLightOn = true, Priority: LOW
     * </code>
     * This method uses {@link VehicleDao} and {@link AlertDao} to persist the records.
     *
     * @param reading The {@link Reading} object to be created.
     */
    @Override
    public void create(final Reading reading) {

        if (reading != null) {
            Vehicle vehicle = vehicleDao.findOne(reading.getVin());
            if (vehicle != null) {
                readingDao.save(reading);
                if (reading.getEngineRpm() > vehicle.getReadlineRpm()) {
                    Alert alert = new Alert();
                    alert.setType(AlertType.HIGH);
                    alert.setMessage("Engine RPM is: " + reading.getEngineRpm() + ". The red line RPM is: " + vehicle.getReadlineRpm() + ".");
                    alert.setVehicle(vehicle);
                    alertDao.save(alert);
                }
                if (reading.getFuelVolume() < vehicle.getMaxFuelVolume() / 10) {
                    Alert alert = new Alert();
                    alert.setType(AlertType.MEDIUM);
                    alert.setMessage("Fuel volume is getting low. Please consider re-fuelling");
                    alert.setVehicle(vehicle);
                    alertDao.save(alert);
                }
                if ((reading.getTires().getFrontLeft() < 32 || reading.getTires().getFrontLeft() > 36) ||
                        (reading.getTires().getFrontRight() < 32 || reading.getTires().getFrontRight() > 36) ||
                        (reading.getTires().getRearLeft() < 32 || reading.getTires().getRearLeft() > 36) ||
                        (reading.getTires().getRearRight() < 32 || reading.getTires().getRearRight() > 36)) {

                    Alert alert = new Alert();
                    alert.setType(AlertType.LOW);
                    alert.setMessage("Check tire air pressures!");
                    alert.setVehicle(vehicle);
                    alertDao.save(alert);
                }
                if (reading.isEngineCoolantLow() || reading.isCheckEngineLightOn()) {
                    Alert alert = new Alert();
                    alert.setType(AlertType.LOW);
                    alert.setMessage("Check engine! Refill coolant or service engine!");
                    alert.setVehicle(vehicle);
                    alertDao.save(alert);
                }

            }
        } else {
            throw new BadRequestException("Reading cannot be empty!");
        }
    }
}
