package com.deepakshankar.cartracker.service;

import com.deepakshankar.cartracker.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    public Vehicle findOne(final String id);
    public List<Vehicle> findAll();
    public List<Vehicle> update(final List<Vehicle> vehicles);
    public Vehicle update(final Vehicle vehicle);
    public Vehicle create(final Vehicle vehicle);
    public void delete(final String id, final Vehicle vehicle);
}
