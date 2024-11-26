package org.lafresca.lafrescabackend.Orchestrator;

import lombok.Data;

import java.util.Map;

@Data
public class ServiceOrchestrator {
    private final SalesPredictionService salesPredictionService;
    private final StockPredictionService stockPredictionService;

    private Map<String, Map<String, String>> branches;

    public ServiceOrchestrator(SalesPredictionService salesPredictionService, StockPredictionService stockPredictionService) {
        this.salesPredictionService = salesPredictionService;
        this.stockPredictionService = stockPredictionService;
    }

    public Map<String, Map<String, Integer>> getSalesPredictionData(){
        return salesPredictionService.getSalesPredictionData();
    }

    public Map<String, Map<String, Float>> getStockPredictionData(){
        return stockPredictionService.getStockPredictionData();
    }

    public Map<String, Integer> getSalesPredictionDataByCafe(String cafeId){
        return salesPredictionService.getSalesPredictionDataByCafe(cafeId);
    }

    public Map<String, Float> getStockPredictionDataByCafe(String cafeId){
        return stockPredictionService.getStockPredictionDataByCafe(cafeId);
    }


}
