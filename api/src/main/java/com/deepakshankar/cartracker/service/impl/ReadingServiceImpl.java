package com.deepakshankar.cartracker.service.impl;

import com.deepakshankar.cartracker.dao.AlertDao;
import com.deepakshankar.cartracker.dao.ReadingDao;
import com.deepakshankar.cartracker.dao.VehicleDao;
import com.deepakshankar.cartracker.entity.*;
import com.deepakshankar.cartracker.exceptions.BadRequestException;
import com.deepakshankar.cartracker.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Reading create(final Reading reading) {
        Reading savedReading = null;
        if (reading != null) {
            Vehicle vehicle = vehicleDao.findOne(reading.getVin());
            if (vehicle != null) {
                savedReading = readingDao.save(reading);
            }
        } else {
            throw new BadRequestException("Reading cannot be empty!");
        }
        return savedReading;
    }

    /**
     * This method should be implemented by any ReadingService implementation to get {@link Reading}s for any vehicle
     * with specified vin number.
     *
     * @param vin the vin of the {@link Vehicle}
     * @return list of {@link Reading}s for the vehicle
     */
    @Override
    public List<Reading> getReadingsForVehicle(String vin) {
        final Vehicle vehicle = vehicleDao.findOne(vin);
        if (vehicle == null) {
            throw new BadRequestException("Invalid vehicle id!");
        } else {
            return readingDao.findReadingsByVin(vin);
        }
    }

}
