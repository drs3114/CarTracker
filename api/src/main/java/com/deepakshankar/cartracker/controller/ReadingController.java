package com.deepakshankar.cartracker.controller;

import com.deepakshankar.cartracker.entity.Reading;
import com.deepakshankar.cartracker.exceptions.BadRequestException;
import com.deepakshankar.cartracker.service.AlertService;
import com.deepakshankar.cartracker.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a controller that handles all the REST calls with URI pattern /tracker/api/readings/*
 *
 * @author Deepak Shankar
 */
@CrossOrigin
@RestController
@RequestMapping(path = "readings")
public class ReadingController {


    @Autowired
    private ReadingService readingService;

    @Autowired
    private AlertService alertService;


    /**
     * This method is used to create readings for the {@link com.deepakshankar.cartracker.entity.Vehicle} objects
     * that are already stored in the database. This method delegates this service to a {@link ReadingService}
     * implementation
     *
     * @param reading The {@link Reading} object that has all the necessary values
     *                for a given {@link com.deepakshankar.cartracker.entity.Vehicle}
     */
    @RequestMapping(method = RequestMethod.POST)
    public void addReading(@RequestBody final Reading reading) {

        if (reading != null) {

            Reading savedReading = readingService.create(reading);
            alertService.createAlertForReading(reading);

        }

    }

    @GetMapping(path = "{id}")
    public List<Reading> getReadingsForVehicle(@PathVariable("id") final String id) {
        if (id != null && id != "") {
            return readingService.getReadingsForVehicle(id);
        } else {
            throw new BadRequestException("Invalid id specified");
        }
    }
}
