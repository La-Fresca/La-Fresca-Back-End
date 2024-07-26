package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.Stock;
import org.lafresca.lafrescabackend.Repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        LocalDate now = LocalDate.now();

        if (stock.getName() == null || stock.getName().isEmpty()) {
            error = "Stock name cannot be empty";
        }
        else if (stock.getBatchId() == null || stock.getBatchId().isEmpty()) {
            error = "Batch id cannot be empty";
        }
        else if (stock.getAvailableAmount() < 0) {
            error = "Invalid value for Available amount";
        }
        else if(LocalDate.parse(stock.getExpiryDate().toString()).isBefore(now)) {
            error = "Expiry date is before current date";
        }
        else if (stock.getSupplierName() == null || stock.getSupplierName().isEmpty()) {
            error = "Supplier name cannot be empty";
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
        Stock existingStock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock not found with id: " + id));
         LocalDate now = LocalDate.now();

        if (stock.getName() != null && !stock.getName().isEmpty()) {
            existingStock.setName(stock.getName());
        }
        if (stock.getBatchId() != null && !stock.getBatchId().isEmpty()) {
            existingStock.setBatchId(stock.getBatchId());
        }
        if (stock.getAvailableAmount() >= 0) {
            existingStock.setAvailableAmount(stock.getAvailableAmount());
        }
        if (stock.getExpiryDate() != null && !stock.getExpiryDate().toString().isEmpty() && !LocalDate.parse(stock.getExpiryDate().toString()).isBefore(now)) {
            existingStock.setExpiryDate(stock.getExpiryDate());
        }
        if (stock.getSupplierName() != null && !stock.getSupplierName().isEmpty()) {
            existingStock.setSupplierName(stock.getSupplierName());
        }

        stockRepository.save(existingStock);
    }
}
