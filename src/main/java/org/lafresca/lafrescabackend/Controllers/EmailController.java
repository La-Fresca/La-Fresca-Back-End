package org.lafresca.lafrescabackend.Controllers;


import org.lafresca.lafrescabackend.Models.EmailStructure;
import org.lafresca.lafrescabackend.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Email Controller")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(path = "{id}")
    @Operation(
            description = "Send email",
            summary = "Send email",
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
}
