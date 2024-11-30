package org.lafresca.lafrescabackend.DTO;

import org.lafresca.lafrescabackend.Models.Stock;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StockDTOMapper implements Function<Stock, StockDTO> {
    @Override
    public StockDTO apply (Stock stock) {
        return new StockDTO(
                stock.getId(),
                stock.getStockCollectionName(),
                stock.getBatchId(),
                stock.getInitialAmount(),
                stock.getSupplierName(),
                stock.getExpiryDate(),
                stock.getUnitPrice(),
                stock.getUnit(),
                stock.getCafeId(),
                stock.getImage()
        );
    }
}
