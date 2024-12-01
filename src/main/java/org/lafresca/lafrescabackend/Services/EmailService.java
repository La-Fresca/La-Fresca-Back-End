package org.lafresca.lafrescabackend.Services;

import lombok.extern.slf4j.Slf4j;
import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.EmailStructure;
import org.lafresca.lafrescabackend.Models.User;
import org.lafresca.lafrescabackend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;
    private final  SystemLogService systemLogService;

    public EmailService(SystemLogService systemLogService) {
        this.systemLogService = systemLogService;
    }

    public String sendOTP(String userId, String OTP) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id " + userId));
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Your One-Time Password");
        message.setText("<div>"
                + "Hello " + user.getFirstName() + " " + user.getLastName() + ",\n"
                + "</div>"
                + "<div>"
                + "Thank you for registering with us! To complete your registration and verify your email address, please use the following One-Time Password (OTP)\n"
                + "Your OTP: " + OTP
                + "</div>"
                + "<div>"
                + "\nThis OTP is valid for the next 5 minutes and can be used only once. Please enter this OTP in the designated field to proceed with the verification process.\n"
                + "</div>"
                + "<div>"
                + "Thank you for choosing La Fresca. We look forward to welcoming you to our community of coffee lovers and food enthusiasts!\n"
                + "La Fresca Team"
                + "</div>");
        message.setFrom("thecafe.lafresca@gmail.com");

        javaMailSender.send(message);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String logmessage = now + " " + username + " " + "Sent email for one time password when registration" ;
        systemLogService.writeToFile(logmessage);
        log.info(logmessage);

        return "Email sent successfully";
    }

    public String sendMonthlyStatement(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id " + userId));
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Monthly Income Statement Overview - [Month, Year]");
        message.setText("<div>"
                + "Dear " + user.getFirstName() + " " + user.getLastName() + ",\n"
                + "</div>"
                + "<div>"
                + "This automated notification serves to inform you that the monthly income statement for La Fresca's [Branch Name] Branch for [Month, Year] is now available. The attached document provides a detailed overview of our financial performance including revenues, expenses, and net income.\n"
                + "</div>"
                + "We kindly request that you review the attached income statement to assess our financial standing and to aid in strategic decision-making for the coming months.\n"
                + "</div>"
                + "<div>"
                + "Should you encounter any discrepancies or require further details, please contact our finance department directly at [Finance Department Contact Information].\n"
                + "</div>"
                + "<div>"
                + "We appreciate your oversight and guidance.\n"
                + "Warm regards,\n"
                + "La Fresca Finance Team"
                + "</div>");
        message.setFrom("thecafe.lafresca@gmail.com");

        javaMailSender.send(message);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String logmessage = now + " " + username + " " + "Sent email for monthly income statement"  ;
        systemLogService.writeToFile(logmessage);
        log.info(logmessage);

        return "Email sent successfully";
    }

    public String sendInvoice(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id " + userId));
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Your La Fresca Order Receipt - Order #[Order Number]\n");
        message.setText("<div>"
                + "Dear " + user.getFirstName() + " " + user.getLastName() + ",\n"
                + "</div>"
                + "<div>"
                + "Thank you for choosing La Fresca! This email confirms your recent order placed on [Order Date]. Below, you'll find a detailed receipt of your purchase.\n"
                + "</div>"
                + "<div>"
                + "Order Summary:\n"
                + "</div>"
                + "<div>"
                + "Order Number: [Order Number]\n" +
                "Date: [Order Date]\n" +
                "Total Amount: [Total Amount]\n" +
                "Payment Method: [Payment Method]\n"
                + "</div>"
                + "<div>"
                + "Items Ordered:\n"
                + "</div>"
                + "<div>"
                + "Item 1 - Quantity: X - Price: $Y\n" +
                "Item 2 - Quantity: X - Price: $Y ... (additional items as necessary)\n"
                + "</div>"
                + "<div>"
                + "Delivery Details:\n"
                + "</div>"
                + "<div>"
                + "Delivery Address: [Customer’s Address]\n" +
                "Estimated Delivery Time: [Delivery Time]\n"
                + "</div>"
                + "<div>"
                + "We hope you enjoy your meal and look forward to serving you again soon.\n"
                + "</div>"
                + "<div>"
                + "Contact us at [Contact Information] for any inquiries.\n"
                + "</div>");
        message.setFrom("thecafe.lafresca@gmail.com");

        javaMailSender.send(message);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String logmessage = now + " " + username + " " + "Email sent for Order reciept"  ;
        systemLogService.writeToFile(logmessage);
        log.info(logmessage);

        return "Email sent successfully";
    }

    public String sendOngoingDiscount(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id " + userId));
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Exclusive Discounts Just for You at La Fresca!");
        message.setText("<div>"
                + "Dear " + user.getFirstName() + " " + user.getLastName() + ",\n"
                + "</div>"
                + "<div>"
                + "We’re excited to share some fantastic news! As a valued customer of La Fresca, you are eligible for exclusive discounts that make enjoying your favorite meals and drinks even more delightful.\n"
                + "</div>"
                + "<div>"
                + "Current Offers:\n"
                + "</div>"
                + "<div>"
                + "[Discount Description 1]: Save [Percentage]% on [Specific Items or Total Order].\n" +
                "[Discount Description 2]: Buy one [Specific Items] and get one [Specific Items] Free!!\n"
                + "</div>"
                + "<div>"
                + "Don’t miss out—these discounts are available for a limited time only!\n"
                + "</div>"
                + "<div>"
                + "Enjoy your savings and your next meal at La Fresca!\n"
                + "</div>");
        message.setFrom("thecafe.lafresca@gmail.com");

        javaMailSender.send(message);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String logmessage = now + " " + username + " " + "Email sent for announce discount";
        systemLogService.writeToFile(logmessage);
        log.info(logmessage);

        return "Email sent successfully";
    }
}
