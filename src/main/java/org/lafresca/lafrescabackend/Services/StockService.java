package org.lafresca.lafrescabackend.Services;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.lafresca.lafrescabackend.DTO.Request.StockRequestDTO;

import org.lafresca.lafrescabackend.DTO.StockDTO;
import org.lafresca.lafrescabackend.DTO.StockDTOMapper;
import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.Stock;
import org.lafresca.lafrescabackend.Models.StockCollection;
import org.lafresca.lafrescabackend.Repositories.StockCollectionRepository;
import org.lafresca.lafrescabackend.Repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StockService {
    private final StockRepository stockRepository;
    private final StockCollectionRepository stockCollectionRepository;
    private final StockDTOMapper stockDTOMapper;

    @Autowired

    public StockService(StockRepository stockRepository, StockCollectionRepository stockCollectionRepository, StockDTOMapper stockDTOMapper) {
        this.stockRepository = stockRepository;

        this.stockCollectionRepository = stockCollectionRepository;
        this.stockDTOMapper = stockDTOMapper;
    }

    // Add new stock
    @Transactional
    public ResponseEntity<StockRequestDTO> addNewStock(@Valid StockRequestDTO stock) {
        Stock newStock = new Stock();
        StockCollection stockCollection = stockCollectionRepository.findByName(stock.getCafeId(), stock.getStockCollectionName());
        if (stockCollection == null) {
            log.error("Stock Collection '{}' Not Found", stock.getStockCollectionName());
            return ResponseEntity.status(404).body(stock);
        }
        else {
            stockCollection.setAvailableAmount(stockCollection.getAvailableAmount() + stock.getInitialAmount());
            stockCollectionRepository.save(stockCollection);

            newStock.setDeleted(0);
            newStock.setUnit(stockCollection.getUnit().toLowerCase());
        }

        newStock.setStockCollectionName(stock.getStockCollectionName());
        newStock.setBatchId(stock.getBatchId());
        newStock.setInitialAmount(stock.getInitialAmount());
        newStock.setSupplierName(stock.getSupplierName());
        newStock.setExpiryDate(stock.getExpiryDate());
        newStock.setCafeId(stock.getCafeId());
        newStock.setUnitPrice(stock.getUnitPrice());
        newStock.setImage(stockCollection.getImage());

        stockRepository.save(newStock);
        log.info("Stock Added Successfully");

        return ResponseEntity.status(201).body(stock);

    }

    // Get all stocks
    public List<StockDTO> getStocks() {
        return stockRepository.findByDeleted()
                .stream()
                .map(stockDTOMapper)
                .collect(Collectors.toList());
    }

    // Get all stocks by Cafe Id
    public List<StockDTO> getStocks(String cafeId) {
        return stockRepository.findByCafeId(cafeId)
                .stream()
                .map(stockDTOMapper)
                .collect(Collectors.toList());
    }

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
    @Transactional
    public void logicallyDeleteStock(String id, Stock stock) {
        Stock existingStock = stockRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Stock not found with id " + id));

        StockCollection stockCollection = stockCollectionRepository.findByName(existingStock.getCafeId(), existingStock.getStockCollectionName());
        stockCollection.setAvailableAmount(stockCollection.getAvailableAmount() - existingStock.getInitialAmount());
        stockCollectionRepository.save(stockCollection);

        existingStock.setDeleted(1);
        stockRepository.save(existingStock);
    }

    // Get stock by stock collection name
    public List<Stock> getStockByCollectionName(String cafeId, String stockCollectionName) {
        List<Stock> list = stockRepository.findByName(cafeId, stockCollectionName);
        return list;

    }
}