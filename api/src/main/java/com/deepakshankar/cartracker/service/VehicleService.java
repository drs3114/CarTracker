package com.deepakshankar.cartracker.service;

import com.deepakshankar.cartracker.entity.Vehicle;

import java.util.List;

/**
 * This is a service interface that defines all the necessary methods a {@link VehicleService} implementation should provide
 * to qualify as a vehicle service.
 *
 * @author Deepak Shankar
 */
public interface VehicleService {

    /**
     * This method should provide the implementation to find a {@link Vehicle} object based on the id (vin) passed.
     *
     * @param id The vin of the vehicle
     * @return the {@link Vehicle} object if found. Error otherwise.
     */
    public Vehicle findOne(final String id);

    /**
     * This method should provide the implementation to find all the {@link Vehicle} objects stored in the database.
     *
     * @return A list of {@link Vehicle} objects if there are any. An empty list otherwise.
     */
    public List<Vehicle> findAll();

    /**
     * This method should provide the implementation to update a list of {@link Vehicle} objects.
     *
     * @param vehicles a list of {@link Vehicle} objects
     * @return the updated list of vehicles.
     */
    public List<Vehicle> update(final List<Vehicle> vehicles);

    /**
     * This method should provide the implementation to update a single {@link Vehicle} object.
     *
     * @param vehicle the {@link Vehicle} object that has to be updated
     * @return the updated {@link Vehicle} objcet. Error otherwise.
     */
    public Vehicle update(final Vehicle vehicle);

    /**
     * This method should provide the implementation to create a new {@link Vehicle} object.
     *
     * @param vehicle the new object to be created and stored.
     * @return the {@link Vehicle} object created if successful. Error otherwise.
     */
    public Vehicle create(final Vehicle vehicle);

    /**
     * This method should provide the implementation to delete a {@link Vehicle} object.
     *
     * @param id      the vin of the {@link Vehicle} object to be deleted
     * @param vehicle the {@link Vehicle} object that has to be deleted.
     */
    public void delete(final String id, final Vehicle vehicle);
}
