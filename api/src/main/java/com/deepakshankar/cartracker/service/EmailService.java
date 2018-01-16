package com.deepakshankar.cartracker.service;

import com.deepakshankar.cartracker.entity.Email;

/**
 * Implement this interface to get the ability to send emails to users as notification.
 */
public interface EmailService {

    /**
     * implement this method to save the notification to the database and then send it to the appropriate user.
     *
     * @param email the {@link Email} object that has all the necessary information to send the email.
     */
    void saveAndSendEmail(Email email);

}
