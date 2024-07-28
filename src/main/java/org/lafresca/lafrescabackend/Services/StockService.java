package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.FoodCombo;
import org.lafresca.lafrescabackend.Models.Stock;
import org.lafresca.lafrescabackend.Repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) { this.stockRepository = stockRepository; }

    // Add new stock
    public String addNewStock(Stock stock) {
        String error = null;

        LocalDateTime now = LocalDateTime.now();


        if (stock.getStockCollectionName() == null || stock.getStockCollectionName().isEmpty()) {
            error = "Stock collection name cannot be empty";
        }
        else if (stock.getBatchId() == null || stock.getBatchId().isEmpty()) {
            error = "Batch id cannot be empty";
        }
        else if (stock.getInitialAmount() < 0) {
            error = "Invalid value for initial amount";
        }
        else if (stock.getDeleted() == null) {
            stock.setDeleted(0);
        }

        else if (stock.getLowerLimit() < 0) {
            error = "Invalid value for Lower limit";
        }
        else if(LocalDateTime.parse(stock.getExpiryDate().toString()).isBefore(now)) {
            error = "Expiry date is before current date";
        }

        if (error == null) {
            stockRepository.save(stock);
        }

        return error;
    }

    // Get all stocks
    public List<Stock> getStocks() { return stockRepository.findAll(); }

    // Get stock by id
    public Optional<Stock> getStock(String id) {
        stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock not found with id: " + id));
        return stockRepository.findById(id);
    }

    public void deleteStock(String id) {
        stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock not found with id: " + id));
        stockRepository.deleteById(id);
    }

    public void updateStock(String id, Stock stock) {
        Stock existingStock = stockRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Stock not found with id: " + id));
         LocalDate now = LocalDate.now();

        if (stock.getStockCollectionName() != null && !stock.getStockCollectionName().isEmpty()) {
            existingStock.setStockCollectionName(stock.getStockCollectionName());
        }
        if (stock.getBatchId() != null && !stock.getBatchId().isEmpty()) {
            existingStock.setBatchId(stock.getBatchId());
        }
        if (stock.getInitialAmount() >= 0) {
            existingStock.setInitialAmount(stock.getInitialAmount());
        }
        if (stock.getLowerLimit() >= 0) {
            existingStock.setLowerLimit(stock.getLowerLimit());
        }
        else if(stock.getExpiryDate() != null && !stock.getExpiryDate().toString().isEmpty() && !LocalDateTime.parse(stock.getExpiryDate().toString()).isBefore(now)) {
            existingStock.setExpiryDate(stock.getExpiryDate());

        }

        stockRepository.save(existingStock);
    }

    // Logical Delete
    public void logicallyDeleteStock(String id, Stock stock) {
        Stock existingStock = stockRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Stock not found with id " + id));

        existingStock.setDeleted(stock.getDeleted());

        stockRepository.save(existingStock);
    }
}
