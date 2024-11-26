package org.lafresca.lafrescabackend.Orchestrator;

import lombok.Data;

@Data
public class ServiceOrchestrator {
    private final SalesPredictionService salesPredictionService;
    private final StockPredictionService stockPredictionService;

    public ServiceOrchestrator(SalesPredictionService salesPredictionService, StockPredictionService stockPredictionService) {
        this.salesPredictionService = salesPredictionService;
        this.stockPredictionService = stockPredictionService;
    }
}
