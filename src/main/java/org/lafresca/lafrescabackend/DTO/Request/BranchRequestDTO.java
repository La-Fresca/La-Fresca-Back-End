package org.lafresca.lafrescabackend.DTO.Request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BranchRequestDTO {
    @NotEmpty(message = "Address cannot be empty")
    @NotNull(message = "Address cannot be null")
    private String Address;

    @NotEmpty(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    private String Name;

    @NotEmpty(message = "Contact Number cannot be empty")
    @NotNull(message = "Contact Number cannot be null")
    private String ContactNo;

    @NotNull(message = "Longitude cannot be null")
    private Double Longitude;

    @NotNull(message = "Longitude cannot be null")
    private Double Latitude;

    @NotEmpty(message = "Branch Manager cannot be empty")
    @NotNull(message = "Branch Manager cannot be null")
    private String BranchManager;
}
