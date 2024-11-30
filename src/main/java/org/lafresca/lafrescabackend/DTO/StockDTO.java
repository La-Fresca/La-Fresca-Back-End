package org.lafresca.lafrescabackend.DTO;

import java.time.LocalDate;

public record StockDTO(
         String id,
         String StockCollectionName,
         String BatchId,
         Double InitialAmount,
         String SupplierName,
         LocalDate ExpiryDate,
         Double UnitPrice,
         String Unit,
         String CafeId,
         String Image
) {
}
