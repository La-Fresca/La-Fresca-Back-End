package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.Stock;
import org.lafresca.lafrescabackend.Models.StockCollection;
import org.lafresca.lafrescabackend.Repositories.StockCollectionRepository;
import org.lafresca.lafrescabackend.Repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockCollectionService {
    private final StockCollectionRepository stockCollectionRepository;
    private final StockRepository stockRepository;

    @Autowired
    private StockCollectionService(StockCollectionRepository stockCollectionRepository, StockRepository stockRepository) {
        this.stockCollectionRepository = stockCollectionRepository;
        this.stockRepository = stockRepository;
    }

    // Add New Stock Collection
    public String addNewStockCollection(StockCollection stockCollection) {
        String error = null;

        if (stockCollection.getName() == null || stockCollection.getName().isEmpty()) {
            error = "Stock collection name cannot be empty";
        }
        else if (stockCollection.getAvailableAmount() == null) {
            error = "Stock collection available amount cannot be null";
        }
        else if (stockCollection.getAvailableAmount() != null) {
            List<Stock> stockList = stockRepository.findByStockCollectionName(stockCollection.getName());

            double availableAmount = 0;

            if (stockList != null) {
                for (Stock stock : stockList) {
                    availableAmount += stock.getInitialAmount();
                }

                stockCollection.setAvailableAmount(availableAmount);
            }

            else {
                error = "Stock collection does not exist";
            }
        }
        else if (stockCollection.getLowerLimit() == null) {
            error = "Stock collection lower limit cannot be null";
        }
        else if (stockCollection.getLowerLimit() > stockCollection.getAvailableAmount()) {
            error = "Invalid value for lower limit";
        }

        if (error == null) {
            stockCollectionRepository.save(stockCollection);
        }

        return error;
    }

    // Get all stock collections
    public List<StockCollection> getStockCollections() {
        return stockCollectionRepository.findAll();
    }

    // Get stock collection by id
    public Optional<StockCollection> getStockCollection(String id) {
        stockCollectionRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Stock collection with id " + id + " not found"));
        return stockCollectionRepository.findById(id);
    }

    // Delete stock by id
    public void deleteStockCollection(String id) {
        stockCollectionRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Stock collection with id " + id + " not found"));
        stockCollectionRepository.deleteById(id);
    }

    // Update stock by id
    public void updateStockCollection(String id, StockCollection stockCollection) {
        StockCollection existingStockCollection = stockCollectionRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Stock Collection not found with Id: " + id));

        if (stockCollection.getName() != null && !stockCollection.getName().isEmpty()) {
            existingStockCollection.setName(stockCollection.getName());
        }
        if (stockCollection.getLowerLimit() != null && stockCollection.getLowerLimit() > 0) {
            existingStockCollection.setLowerLimit(stockCollection.getLowerLimit());
        }
        if (stockCollection.getAvailableAmount() != null && stockCollection.getAvailableAmount() > 0) {
            existingStockCollection.setAvailableAmount(stockCollection.getAvailableAmount());
        }

        stockCollectionRepository.save(existingStockCollection);
    }

}
