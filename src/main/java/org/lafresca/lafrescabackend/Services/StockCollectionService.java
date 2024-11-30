package org.lafresca.lafrescabackend.Services;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.lafresca.lafrescabackend.DTO.Request.StockCollectionRequestDTO;

import org.lafresca.lafrescabackend.DTO.StockCollectionDTO;
import org.lafresca.lafrescabackend.DTO.StockCollectionDTOMapper;
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
import java.util.stream.Collectors;

@Service
@Slf4j
public class StockCollectionService {
    private final StockCollectionRepository stockCollectionRepository;
    private final StockRepository stockRepository;
    private final StockCollectionDTOMapper stockCollectionDTOMapper;

    @Autowired
    private StockCollectionService(StockCollectionRepository stockCollectionRepository, StockRepository stockRepository, StockCollectionDTOMapper stockCollectionDTOMapper) {
        this.stockCollectionRepository = stockCollectionRepository;
        this.stockRepository = stockRepository;
        this.stockCollectionDTOMapper = stockCollectionDTOMapper;
    }

    // Add New Stock Collection
    public StockCollectionRequestDTO addNewStockCollection(@Valid StockCollectionRequestDTO stockCollection) {
        StockCollection newStockCollection = new StockCollection();

        newStockCollection.setName(stockCollection.getName());
        newStockCollection.setUnit(stockCollection.getUnit().toLowerCase());
        newStockCollection.setLowerLimit(stockCollection.getLowerLimit());
        newStockCollection.setCafeId(stockCollection.getCafeId());
        newStockCollection.setImage(stockCollection.getImage());

        newStockCollection.setDeleted(0);
        newStockCollection.setPredictedStockoutDate(LocalDate.now());
        newStockCollection.setStatus("");
        newStockCollection.setAvailableAmount(0.0);

        stockCollectionRepository.save(newStockCollection);

        return stockCollection;
    }

    // Get all stock collections
    public List<StockCollectionDTO> getStockCollections() {
        List<StockCollection> stockCollections = stockCollectionRepository.findByDeleted();
        for (StockCollection stockCollection : stockCollections) {
            if (stockCollection.getLowerLimit() < stockCollection.getAvailableAmount()){
                stockCollection.setStatus("High stock");
            }
            else if (stockCollection.getLowerLimit().equals(stockCollection.getAvailableAmount())){
                stockCollection.setStatus("Low stock");
            }
            else {
                stockCollection.setStatus("Out of stock");
            }

            stockCollection.setPredictedStockoutDate(LocalDate.now());
        }
        return stockCollections
                .stream()
                .map(stockCollectionDTOMapper)
                .collect(Collectors.toList());
    }

    // Get all stock collections by CafeId
    public List<StockCollectionDTO> getStockCollections(String cafeId) {
        List<StockCollection> stockCollections = stockCollectionRepository.findByCafeId(cafeId);
        for (StockCollection stockCollection : stockCollections) {
            if (stockCollection.getLowerLimit() < stockCollection.getAvailableAmount()){
                stockCollection.setStatus("High stock");
            }
            else if (stockCollection.getLowerLimit().equals(stockCollection.getAvailableAmount())){
                stockCollection.setStatus("Low stock");
            }
            else {
                stockCollection.setStatus("Out of stock");
            }

            stockCollection.setPredictedStockoutDate(LocalDate.now());
        }
        return stockCollections
                .stream()
                .map(stockCollectionDTOMapper)
                .collect(Collectors.toList());
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
        if (stockCollection.getUnit() != null && !stockCollection.getUnit().isEmpty()) {
            existingStockCollection.setUnit(stockCollection.getUnit());
        }


        stockCollectionRepository.save(existingStockCollection);
    }

    // Logical Delete
    public void logicallyDeleteStockCollection(String id) {
        StockCollection existingStockCollection = stockCollectionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Stock Collection not found with id " + id));

        existingStockCollection.setDeleted(1);
        String collectionName = existingStockCollection.getName();
        String cafeId = existingStockCollection.getCafeId();
        List<Stock> StockList = stockRepository.findByName(cafeId, collectionName);

        for (Stock stock : StockList) {
            stock.setDeleted(1);
            stockRepository.save(stock);
        }

        stockCollectionRepository.save(existingStockCollection);
    }
}