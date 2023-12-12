package com.example.backend.mailerservice;

import javax.mail.MessagingException;

public interface MailService {
    void sendMail(String to, String subject, String text) throws MessagingException, jakarta.mail.MessagingException;
}
