package com.deepakshankar.cartracker.controller;

import com.deepakshankar.cartracker.entity.Email;
import com.deepakshankar.cartracker.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("notification")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping()
    public void saveAndSendEmail(@RequestBody final Email email) {

        emailService.saveAndSendEmail(email);

    }
}
