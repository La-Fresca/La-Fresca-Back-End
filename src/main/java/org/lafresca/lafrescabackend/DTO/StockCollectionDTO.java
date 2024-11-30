package org.lafresca.lafrescabackend.DTO;

import java.time.LocalDate;

public record StockCollectionDTO(
        String id,
        String Name,
        String Unit,
        Double LowerLimit,
        Double AvailableAmount,
        LocalDate PredictedStockoutDate,
        String Status,
        String CafeId,
        String Image
) {
}
