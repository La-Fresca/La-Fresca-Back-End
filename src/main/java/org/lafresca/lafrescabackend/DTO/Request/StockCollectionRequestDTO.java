package org.lafresca.lafrescabackend.DTO.Request;

import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockCollectionRequestDTO {
    @NotEmpty(message = "Name cannot be empty")
    private String Name;

    @NotEmpty(message = "Unit cannot be empty")
    private String Unit;

    @NotNull(message = "Lower limit cannot be null")
    private Double LowerLimit;

    @NotEmpty(message = "Cafe ID cannot be empty")
    private String CafeId;

    @NotEmpty(message = "Image cannot be empty")
    private String Image;
}
