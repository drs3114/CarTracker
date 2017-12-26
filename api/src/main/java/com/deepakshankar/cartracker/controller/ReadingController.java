package com.deepakshankar.cartracker.controller;

import com.deepakshankar.cartracker.entity.Reading;
import com.deepakshankar.cartracker.entity.Vehicle;
import com.deepakshankar.cartracker.exceptions.VehicleNotFoundException;
import com.deepakshankar.cartracker.service.ReadingService;
import com.deepakshankar.cartracker.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "readings")
public class ReadingController {


    @Autowired
    private ReadingService readingService;

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.POST)
    public void addReading(@RequestBody final Reading reading){

        Vehicle vehicle = vehicleService.findOne(reading.getVin());
        if(vehicle!=null){
            readingService.create(reading);
        }
        else{
            throw new VehicleNotFoundException();
        }

    }
}
