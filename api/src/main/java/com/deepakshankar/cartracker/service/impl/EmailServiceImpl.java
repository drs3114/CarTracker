package com.deepakshankar.cartracker.service.impl;

import com.deepakshankar.cartracker.dao.EmailDao;
import com.deepakshankar.cartracker.entity.Email;
import com.deepakshankar.cartracker.exceptions.NotificationSendFailedException;
import com.deepakshankar.cartracker.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailDao emailDao;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * implement this method to save the notification to the database and then send it to the appropriate user.
     *
     * @param email the {@link Email} object that has all the necessary information to send the email.
     */
    @Override
    @Transactional(rollbackFor = NotificationSendFailedException.class)
    public void saveAndSendEmail(Email email) throws NotificationSendFailedException {

        Email notificationEmail = new Email();
        SimpleMailMessage message = new SimpleMailMessage();
        if (email.getFromEmail() == null || email.getFromEmail() == "") {
            throw new NotificationSendFailedException("No sender email id Found!");
        }
        if (email.getToEmail() == null || email.getToEmail() == "") {
            throw new NotificationSendFailedException("No recipient email id Found!");
        }

        try {

            notificationEmail.setFromEmail(email.getFromEmail());
            notificationEmail.setToEmail(email.getToEmail());
            notificationEmail.setSubject(email.getSubject());
            notificationEmail.setBody(email.getBody());
            notificationEmail.setVehicle(email.getVehicle());

            message.setFrom(email.getFromEmail());
            message.setTo(email.getToEmail());
            message.setSubject(email.getSubject());
            message.setText(email.getBody());

            javaMailSender.send(message);
            emailDao.save(notificationEmail);
        } catch (MailException me) {
            me.printStackTrace();
            throw new NotificationSendFailedException(me.getMessage());
        }


    }
}
