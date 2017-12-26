package com.deepakshankar.cartracker.service.impl;

import com.deepakshankar.cartracker.dao.VehicleDao;
import com.deepakshankar.cartracker.entity.Vehicle;
import com.deepakshankar.cartracker.exceptions.BadRequestException;
import com.deepakshankar.cartracker.exceptions.VehicleNotFoundException;
import com.deepakshankar.cartracker.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDao vehicleDao;

    @Override
    public Vehicle findOne(final String id) {

        return vehicleDao.findOne(id);

    }

    @Override
    public List<Vehicle> findAll() {

        return vehicleDao.findAll();
    }

    @Override
    public List<Vehicle> update(final List<Vehicle> vehicles) {

        vehicles.forEach(vehicle -> {
            update(vehicle);
        });

        return vehicles;
    }

    @Override
    public Vehicle update(final Vehicle vehicle) {
        if (vehicle != null && vehicle.getVin() != null && vehicle.getVin() != "" && vehicle.getVin().length() > 0) {
            vehicleDao.save(vehicle);
        }
        else{
            if(vehicle==null){
                throw new VehicleNotFoundException();
            }
            throw new BadRequestException("Invalid VIN number!");
        }
        return vehicleDao.findOne(vehicle.getVin());
    }

    @Override
    public Vehicle create(final Vehicle vehicle) {

        return update(vehicle);

    }

    @Override
    public void delete(final String id, final Vehicle vehicle) {
        Vehicle v = vehicleDao.findOne(id);
        if (v == null) {
            throw new VehicleNotFoundException();
        }
        vehicleDao.delete(vehicle);
    }
}
