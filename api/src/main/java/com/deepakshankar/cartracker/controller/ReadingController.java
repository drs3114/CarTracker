package com.deepakshankar.cartracker.controller;

import com.deepakshankar.cartracker.entity.Reading;
import com.deepakshankar.cartracker.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "readings")
public class ReadingController {


    @Autowired
    private ReadingService readingService;

    @RequestMapping(method = RequestMethod.POST)
    public void addReading(@RequestBody final Reading reading) {

        if (reading != null) {

            readingService.create(reading);
        }

    }
}
