package org.lafresca.lafrescabackend.DTO.Request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StockRequestDTO {
    @NotEmpty(message = "StockCollectionName cannot be empty")
    private String StockCollectionName;

    @NotEmpty(message = "BatchId cannot be empty")
    private String BatchId;

    @NotNull(message = "InitialAmount cannot be null")
    private Double InitialAmount;

    @NotEmpty(message = "SupplierName cannot be empty")
    private String SupplierName;

    @NotNull(message = "ExpiryDate cannot be null")
    private LocalDate ExpiryDate;

    @NotEmpty(message = "CafeId cannot be empty")
    private String CafeId;

    @NotNull(message = "UnitPrice cannot be null")
    private Double UnitPrice;

    private String Image;
}
