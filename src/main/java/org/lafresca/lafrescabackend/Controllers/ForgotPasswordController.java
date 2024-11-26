package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.lafresca.lafrescabackend.DTO.Request.ChangePasswordDTO;
import org.lafresca.lafrescabackend.Models.Branch;
import org.lafresca.lafrescabackend.Services.ForgotPasswordService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/lafresca/forgotPassword")
@AllArgsConstructor
@Validated
@Tag(name = "Forgot Password Controller")
public class ForgotPasswordController {
    private final ForgotPasswordService forgotPasswordService;

    @GetMapping(path = "{email}")
    @Operation(
            description = "Get email",
            summary = "Get email to change password",
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

    public String getEmail(@PathVariable("email") String email) {
        return forgotPasswordService.sendEmail(email);
    }

    @PostMapping
    @Operation(
            description = "Change Password",
            summary = "change password",
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

    public String changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        return forgotPasswordService.changePassword(changePasswordDTO);
    }
}
