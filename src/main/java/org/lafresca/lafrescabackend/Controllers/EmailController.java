package org.lafresca.lafrescabackend.Controllers;

import org.lafresca.lafrescabackend.Models.Cart;
import org.lafresca.lafrescabackend.Models.EmailStructure;
import org.lafresca.lafrescabackend.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping
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
    public String sendEmail(@RequestBody EmailStructure email) {
        return emailService.sendEmail(email);
    }
}
