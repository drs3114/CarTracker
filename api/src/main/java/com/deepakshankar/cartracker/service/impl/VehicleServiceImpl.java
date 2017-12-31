package com.deepakshankar.cartracker.service.impl;

import com.deepakshankar.cartracker.dao.VehicleDao;
import com.deepakshankar.cartracker.entity.Vehicle;
import com.deepakshankar.cartracker.exceptions.BadRequestException;
import com.deepakshankar.cartracker.exceptions.VehicleNotFoundException;
import com.deepakshankar.cartracker.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class provides the service implementation as defined in the {@link VehicleService} interface.
 *
 * @author Deepak Shankar
 */
@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDao vehicleDao;

    /**
     * This method provides the implementation to find a {@link Vehicle} object based on the vin by using the
     * {@link VehicleDao}
     *
     * @param id The vin of the vehicle
     * @return Vehicle object if found. Error otherwise.
     */
    @Override
    public Vehicle findOne(final String id) {

        return vehicleDao.findOne(id);

    }

    /**
     * This method provides the implementation to find all the {@link Vehicle} objects using {@link VehicleDao}
     *
     * @return a list of all the {@link Vehicle} objects if there are any. An empty list otherwise.
     */
    @Override
    public List<Vehicle> findAll() {

        return vehicleDao.findAll();
    }

    /**
     * This method provides the implementation to update a list of {@link Vehicle} objects using {@link VehicleDao}.
     * This method internally uses the <code>update(final {@link Vehicle} vehicle)</code> method implementation.
     *
     * @param vehicles a list of {@link Vehicle} objects
     * @return the updated list of {@link Vehicle} objects.
     */
    @Override
    public List<Vehicle> update(final List<Vehicle> vehicles) {

        vehicles.forEach(vehicle -> {
            update(vehicle);
        });

        return vehicles;
    }

    /**
     * This method provides the implementation to update a single {@link Vehicle} object using {@link VehicleDao}
     *
     * @param vehicle the {@link Vehicle} object that has to be updated
     * @return The updated {@link Vehicle} object if successful. Error otherwise.
     */
    @Override
    public Vehicle update(final Vehicle vehicle) {
        if (vehicle != null && vehicle.getVin() != null && vehicle.getVin() != "" && vehicle.getVin().length() > 0) {
            vehicleDao.save(vehicle);
        } else {
            if (vehicle == null) {
                throw new VehicleNotFoundException();
            }
            throw new BadRequestException("Invalid VIN number!");
        }
        return vehicleDao.findOne(vehicle.getVin());
    }

    /**
     * This method provides the implementation to create a new {@link Vehicle object}. This method uses the <code>update
     * (final
     * {@link Vehicle} vehicle)</code> method to provide the implementation.
     *
     * @param vehicle the new object to be created and stored.
     * @return the newly created {@link Vehicle} object if successful. Error otherwise.
     */
    @Override
    public Vehicle create(final Vehicle vehicle) {

        return update(vehicle);

    }

    /**
     * This method provides the implementation to delete an existing {@link Vehicle} object using the {@link VehicleDao}
     *
     * @param id      the vin of the {@link Vehicle} object to be deleted
     * @param vehicle the {@link Vehicle} object that has to be deleted.
     */
    @Override
    public void delete(final String id, final Vehicle vehicle) {
        Vehicle v = vehicleDao.findOne(id);
        if (v == null) {
            throw new VehicleNotFoundException();
        }
        vehicleDao.delete(vehicle);
    }
}
