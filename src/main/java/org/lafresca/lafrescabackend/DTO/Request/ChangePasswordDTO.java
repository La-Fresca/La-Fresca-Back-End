package org.lafresca.lafrescabackend.DTO.Request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ChangePasswordDTO {
    @NotEmpty(message = "email cannot be empty")
    @NotNull(message = "email cannot be null")
    private String email;

    @NotEmpty(message = "password cannot be empty")
    @NotNull(message = "password cannot be null")
    private String newPassword;

    @NotEmpty(message = "random no cannot be empty")
    @NotNull(message = "random no cannot be null")
    private String randomNo;

}
