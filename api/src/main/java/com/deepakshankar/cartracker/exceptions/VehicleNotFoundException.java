package com.deepakshankar.cartracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such vehicle found!")
public class VehicleNotFoundException extends RuntimeException {
}
