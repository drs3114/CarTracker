package com.deepakshankar.cartracker.controller;

import com.deepakshankar.cartracker.entity.Reading;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "readings")
public class ReadingController {

    @RequestMapping(method = RequestMethod.POST)
    public void addReading(@RequestBody final Reading reading){

    }
}
