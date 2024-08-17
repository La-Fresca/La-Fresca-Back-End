package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.Stock;
import org.lafresca.lafrescabackend.Models.StockCollection;
import org.lafresca.lafrescabackend.Repositories.StockCollectionRepository;
import org.lafresca.lafrescabackend.Repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    private final StockRepository stockRepository;
    private final StockCollectionRepository stockCollectionRepository;

    @Autowired
    public StockService(StockRepository stockRepository, StockCollectionRepository stockCollectionRepository) { this.stockRepository = stockRepository;
        this.stockCollectionRepository = stockCollectionRepository;
    }

    // Add new stock
    public String addNewStock(Stock stock) {
        String error = null;
        LocalDate now = LocalDate.now();

        if (stock.getStockCollectionName() == null || stock.getStockCollectionName().isEmpty()) {
            error = "Stock collection name cannot be empty";
        }
        else if (stock.getBatchId() == null || stock.getBatchId().isEmpty()) {
            error = "Batch id cannot be empty";
        }
        else if (stock.getInitialAmount() < 0) {
            error = "Invalid value for initial amount";
        }
        else if (stock.getUnitPrice() == null) {
            error = "Unit price cannot be null";
        }
        else if (stock.getUnitPrice() < 0) {
            error = "Invalid value for unit price";
        }
        else if (stock.getDeleted() == null) {
            stock.setDeleted(0);
        }
        else if(LocalDate.parse(stock.getExpiryDate().toString()).isBefore(now)) {
            error = "Expiry date is before current date";
        }
        else if (stock.getSupplierName() == null || stock.getSupplierName().isEmpty()) {
            error = "Supplier name cannot be empty";
        }

        if (error == null) {
            StockCollection stockCollection = stockCollectionRepository.findByName(stock.getStockCollectionName());
            if (stockCollection == null) {
                StockCollection newStockCollection = new StockCollection();
                newStockCollection.setName(stock.getStockCollectionName());
                newStockCollection.setDeleted(0);
                newStockCollection.setLowerLimit(0.0);
                newStockCollection.setAvailableAmount(stock.getInitialAmount());

                stockCollectionRepository.save(newStockCollection);
            }
            else {
//                stockCollection.setName(stockCollection.getId());
                stockCollection.setAvailableAmount(stockCollection.getAvailableAmount() + stock.getInitialAmount());
                stockCollectionRepository.save(stockCollection);
            }

            stockRepository.save(stock);
        }

        return error;
    }

    // Get all stocks
    public List<Stock> getStocks() { return stockRepository.findByDeleted(0); }

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
        if (stock.getUnitPrice() > 0) {
            existingStock.setUnitPrice(stock.getUnitPrice());
        }
        if (stock.getExpiryDate() != null && !stock.getExpiryDate().toString().isEmpty() && !LocalDate.parse(stock.getExpiryDate().toString()).isBefore(now)) {
            existingStock.setExpiryDate(stock.getExpiryDate());
        }
        if (stock.getSupplierName() != null && !stock.getSupplierName().isEmpty()) {
            existingStock.setSupplierName(stock.getSupplierName());
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

    // Get stock by stock collection name
    public List<Stock> getStockByCollectionName(String stockCollectionName) {
        return stockRepository.findByName(stockCollectionName);
    }
}