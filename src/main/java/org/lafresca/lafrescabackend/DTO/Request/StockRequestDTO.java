package org.lafresca.lafrescabackend.DTO.Request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.lafresca.lafrescabackend.Validations.ValidAmount;
import org.lafresca.lafrescabackend.Validations.ValidExpiryDate;
import org.lafresca.lafrescabackend.Validations.ValidPrice;

import java.time.LocalDate;

@Getter
@Setter
public class StockRequestDTO {
    @NotEmpty(message = "StockCollectionName cannot be empty")
    private String StockCollectionName;

    @NotEmpty(message = "BatchId cannot be empty")
    private String BatchId;

    @ValidAmount
    private Double InitialAmount;

    @NotEmpty(message = "SupplierName cannot be empty")
    private String SupplierName;

    @ValidExpiryDate
    private LocalDate ExpiryDate;

    @NotEmpty(message = "CafeId cannot be empty")
    private String CafeId;

    @ValidPrice
    private Double UnitPrice;

    private String Image;
}
