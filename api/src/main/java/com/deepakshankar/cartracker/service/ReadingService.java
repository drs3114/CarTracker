package com.deepakshankar.cartracker.service;

import com.deepakshankar.cartracker.entity.Reading;

import java.util.List;

/**
 * This is a service interface that defines all the necessary methods a ReadingService implementation should provide
 * to qualify as a reading service.
 *
 * @author Deepak Shankar
 */
public interface ReadingService {
    /**
     * This method should be implemented by any ReadingService implementation to create a new {@link Reading} object.
     *
     * @param reading The {@link Reading} object to be created.
     */
    public Reading create(final Reading reading);

    /**
     * This method should be implemented by any ReadingService implementation to get {@link Reading}s for any vehicle
     * with specified vin number.
     *
     * @param vin the vin of the {@link com.deepakshankar.cartracker.entity.Vehicle}
     * @return list of {@link Reading}s for the vehicle
     */
    public List<Reading> getReadingsForVehicle(final String vin);
}