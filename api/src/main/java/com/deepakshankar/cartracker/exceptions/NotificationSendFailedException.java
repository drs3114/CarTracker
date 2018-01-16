package com.deepakshankar.cartracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class NotificationSendFailedException extends RuntimeException {

    public NotificationSendFailedException(String reason) {
        super(reason);
    }
}
