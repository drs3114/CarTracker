package com.deepakshankar.cartracker.controller;

import com.deepakshankar.cartracker.entity.Vehicle;
import com.deepakshankar.cartracker.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Vehicle> findAll(){

        return vehicleService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public Vehicle findOne(@PathVariable("id") final String vin){

        return vehicleService.findOne(vin);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public List<Vehicle> update(@RequestBody final List<Vehicle> vehicles){

        return vehicleService.update(vehicles);

    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public Vehicle update(@PathVariable("id") final String id,@RequestBody final Vehicle vehicle){

        return vehicleService.update(vehicle);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Vehicle create(@RequestBody final Vehicle vehicle){

        return vehicleService.create(vehicle);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public void delete(@PathVariable("id") final String vin,@RequestBody final Vehicle vehicle){

        vehicleService.delete(vin,vehicle);

    }


}
