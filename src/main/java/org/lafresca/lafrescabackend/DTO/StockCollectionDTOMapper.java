package org.lafresca.lafrescabackend.DTO;

import org.lafresca.lafrescabackend.Models.StockCollection;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StockCollectionDTOMapper implements Function<StockCollection, StockCollectionDTO> {
    @Override
    public StockCollectionDTO apply (StockCollection stockCollection) {
        return new StockCollectionDTO(
                stockCollection.getId(),
                stockCollection.getName(),
                stockCollection.getUnit(),
                stockCollection.getLowerLimit(),
                stockCollection.getAvailableAmount(),
                stockCollection.getPredictedStockoutDate(),
                stockCollection.getStatus(),
                stockCollection.getCafeId(),
                stockCollection.getImage()
        );
    }
}
