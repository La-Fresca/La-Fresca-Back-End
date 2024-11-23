package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.EmailStructure;
import org.lafresca.lafrescabackend.Models.User;
import org.lafresca.lafrescabackend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;

    public String sendOTP(String userId, String OTP) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id " + userId));
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("suruthi0611@gmail.com");
        message.setSubject("Your One-Time Password");
        message.setText("<div>"
                + "Hello " + user.getFirstName() + " " + user.getLastName() + ","
                + "Your one time password is: " + OTP
                + "Lafresca"
                + "</div>");
        message.setFrom("thecafe.lafresca@gmail.com");

        javaMailSender.send(message);
        return "Email sent successfully";
    }
}
