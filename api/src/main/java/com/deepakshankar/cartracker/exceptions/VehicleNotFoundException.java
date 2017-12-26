package com.deepakshankar.cartracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is a custom exception class that is used when a query on a non-existent
 * {@link com.deepakshankar.cartracker.entity.Vehicle} object is made.
 *
 * @author Deepak Shankar
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such vehicle found!")
public class VehicleNotFoundException extends RuntimeException {
}
