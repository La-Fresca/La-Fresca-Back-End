package org.lafresca.lafrescabackend.Controllers;


import org.lafresca.lafrescabackend.Models.EmailStructure;
import org.lafresca.lafrescabackend.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/api/lafresca/email")
@Tag(name = "Email Controller")
public class EmailController {

    @Autowired
    private EmailService emailService;

    // Send OTP for Email Verification
    @PostMapping(path = "/otp/{id}")
    @Operation(
            description = "Send OTP to Customer to verify their email",
            summary = "Send OTP to Customer",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })
    public String sendOTP(@PathVariable("id") String userId, @RequestBody String OTP) {
        return emailService.sendOTP(userId, OTP);
    }

    // Send Monthly Statement
    @PostMapping(path = "monthlyStatement/{id}")
    @Operation(
            description = "Send Monthly Statement to Branch Manager",
            summary = "Send Monthly Statement",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })
    public String sendMonthlyStatement(@PathVariable("id") String userId) {
        return emailService.sendMonthlyStatement(userId);
    }

    // Send Online Order Invoice
    @PostMapping(path = "customerInvoice/{id}")
    @Operation(
            description = "Send Invoice of Order to Customer",
            summary = "Send Invoice to Customer",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })
    public String sendInvoice(@PathVariable("id") String userId) {
        return emailService.sendInvoice(userId);
    }

    // Send Ongoing Discount Information
    @PostMapping(path = "ongoingDiscount/{id}")
    @Operation(
            description = "Send Ongoing Discount Information to Customer",
            summary = "Send Ongoing Discount Information",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })
    public String sendOngoingDiscount(@PathVariable("id") String userId) {
        return emailService.sendOngoingDiscount(userId);
    }
}
