package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lafresca.lafrescabackend.DTO.Request.StockCollectionRequestDTO;
import org.lafresca.lafrescabackend.DTO.StockCollectionDTO;
import org.lafresca.lafrescabackend.DTO.cafeDTO;
import org.lafresca.lafrescabackend.Models.StockCollection;
import org.lafresca.lafrescabackend.Services.StockCollectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/lafresca/stockCollection")
@AllArgsConstructor
@Validated
@Slf4j
@Tag(name = "Stock Collection Controller")
public class StockCollectionController {
    private final StockCollectionService stockCollectionService;

    // Add New Stock Collection
    @PostMapping
    @Operation(
            description = "Add new stock collection",
            summary = "Add new stock collection to inventory",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })

    public ResponseEntity<StockCollectionRequestDTO> addNewStockCollection(@Valid @RequestBody StockCollectionRequestDTO stockCollection) {
        return ResponseEntity.status(201).body(stockCollectionService.addNewStockCollection(stockCollection));
    }

    // Get all stock collections
    @GetMapping(path = "{cafeId}")
    @Operation(
            description = "Get all stock collections by CafeId",
            summary = "Retrieve all stock collections from inventory by CafeId",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })

    public List<StockCollectionDTO> getStockCollections(@PathVariable("cafeId") String cafeId) { return stockCollectionService.getStockCollections(cafeId); }

    // Search stock collection
    @GetMapping(path = "view/{id}")
    @Operation(
            description = "Get stock collections by id",
            summary = "Retrieve stock collections by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })

    public Optional<StockCollection> getStockCollection(@PathVariable("id") String id) {
        return stockCollectionService.getStockCollection(id);
    }

    // Delete stock collection by id
    @DeleteMapping(path = "{id}")
    @Operation(
            description = "Delete stock collections by id",
            summary = "Delete stock collections by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })

    public void deleteStockCollection(@PathVariable("id") String id) {
        stockCollectionService.deleteStockCollection(id);
    }

    // Update stock collection by id
    @PutMapping(path = "{id}")
    @Operation(
            description = "Update stock collections by id",
            summary = "Update stock collections by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })

    public void updateStockCollection(@PathVariable("id") String id, @RequestBody StockCollection stockCollection) {
        stockCollectionService.updateStockCollection(id, stockCollection);
    }

    // Logical Delete
    @PutMapping(path = "delete/{id}")
    @Operation(
            description = "Logically delete stock collection by id",
            summary = "Logically delete stock collections by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })

    public void logicallyDeleteStockCollection(@PathVariable("id") String id, @RequestBody StockCollection stockCollection){
        stockCollectionService.logicallyDeleteStockCollection(id, stockCollection);
    }
}
