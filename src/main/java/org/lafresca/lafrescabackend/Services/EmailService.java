package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Models.EmailStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public String sendEmail(EmailStructure email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        message.setFrom("thecafe.lafresca@gmail.com");

        javaMailSender.send(message);
        return "Email sent successfully";
    }
}
