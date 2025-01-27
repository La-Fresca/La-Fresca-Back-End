package org.lafresca.lafrescabackend.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String FirstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String LastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String Email;

    @Pattern(regexp = "^\\+?[0-9]*$", message = "Invalid phone number format")
    @Size(max = 10, message = "Phone number cannot exceed 10 digits")
    private String PhoneNumber;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String Address;

    @NotBlank(message = "Role is required")
    private String Role;

    private String CafeId;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "AVAILABLE|ON DELIVERY|ABSENT", message = "Status must be one of the following: AVAILABLE, ON DELIVERY, ABSENT")
    private String status;
}
