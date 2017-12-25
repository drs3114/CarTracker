package com.deepakshankar.cartracker.controller;

import com.deepakshankar.cartracker.entity.Vehicle;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "vehicles")
public class VehicleController {

    @RequestMapping(method = RequestMethod.GET)
    public List<Vehicle> findAll(){

        return null;
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public Vehicle findOne(@PathVariable("id") String vin){
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public List<Vehicle> update(@RequestBody List<Vehicle> vehicles){
        return null;
    }


}
