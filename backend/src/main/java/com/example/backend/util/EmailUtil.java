package com.example.backend.util;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

public class EmailUtil{
    public static void sendEmail(Session session, String toEmail, String subject, String body){
        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
