package com.deepakshankar.cartracker.controller;

import com.deepakshankar.cartracker.entity.Vehicle;
import com.deepakshankar.cartracker.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a controller that handles all the REST calls with URI pattern /tracker/api/vehicles/*
 *
 * @author Deepak Shankar
 */
@CrossOrigin
@RestController
@RequestMapping(path = "vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    /**
     * This method is used to get all the {@link Vehicle} objects stored in the DB.
     *
     * @return A list of {@link Vehicle} objects if there are any. An empty list otherwise.
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Vehicle> findAll() {

        return vehicleService.findAll();
    }

    /**
     * This method is used to get one {@link Vehicle} object based on the vin number of the vehicle.
     *
     * @param vin The vin number to find the {@link Vehicle} object.
     * @return The {@link Vehicle} object if found. Error otherwise.
     */
    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public Vehicle findOne(@PathVariable("id") final String vin) {

        return vehicleService.findOne(vin);
    }

    /**
     * This method is used to update a bulk of {@link Vehicle} objects.
     *
     * @param vehicles A list of {@link Vehicle} objects to be updated
     * @return A list of updated {@link Vehicle} object if successful. Error otherwise.
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Vehicle> update(@RequestBody final List<Vehicle> vehicles) {

        return vehicleService.update(vehicles);

    }

    /**
     * This method is used to update a given {@link Vehicle} object.
     *
     * @param id      The vin of the {@link Vehicle} Object.
     * @param vehicle The {@link Vehicle} object
     * @return The {@link Vehicle} object if successful. Error otherwise.
     */
    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public Vehicle update(@PathVariable("id") final String id, @RequestBody final Vehicle vehicle) {

        return vehicleService.update(vehicle);
    }

    /**
     * This method is used to create a {@link Vehicle}object.
     * The controller uses a {@link VehicleService} implementation to delegate the creation of this object.
     *
     * @param vehicle The vehicle to be created.
     * @return Vehicle object if creation is success. Else, throws an error.
     */
    @RequestMapping(method = RequestMethod.POST)
    public Vehicle create(@RequestBody final Vehicle vehicle) {

        return vehicleService.create(vehicle);
    }

    /**
     * This method is used to delete a {@link Vehicle} object based on the vin number of the vehicle.
     * To perform deletion the controller delegates the operation to the {@link VehicleService} implementation which
     * provides the necessary functionalities to delete it.
     *
     * @param vin     The vin number of the vehicle
     * @param vehicle The {@link Vehicle} object to be deleted
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public void delete(@PathVariable("id") final String vin, @RequestBody final Vehicle vehicle) {

        vehicleService.delete(vin, vehicle);

    }


}
