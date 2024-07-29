package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.lafresca.lafrescabackend.Models.Stock;
import org.lafresca.lafrescabackend.Services.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/lafresca/stock")
@AllArgsConstructor
@Tag(name = "Stock Controller")
public class StockController {
    private final StockService stockService;

    // Add new stock
    @PostMapping
    @Operation(
            description = "Add new stock",
            summary = "Add new stock to inventory",
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

    public String addNewStock(@RequestBody Stock stock) { return stockService.addNewStock(stock); }

    // Get all stock
    @GetMapping
    @Operation(
            description = "Get all stocks",
            summary = "Retrieve all stocks in inventory",
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

    public List<Stock> getStocks() { return stockService.getStocks(); }

    // Search stock by id
    @GetMapping(path = "{id}")
    @Operation(
            description = "Get stock by id",
            summary = "Retrieve stock by using id",
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

    public Optional<Stock> getStock(@PathVariable("id") String id) { return stockService.getStock(id); }

    // Delete stock by id
    @DeleteMapping(path = "{id}")
    @Operation(
            description = "Delete stock by id",
            summary = "Delete stock by using id",
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

    public void deleteStock(@PathVariable("id") String id) { stockService.deleteStock(id);}

    // Update stock by id
    @PutMapping(path = "{id}")
    @Operation(
            description = "Update stock by id",
            summary = "Update stock by using id",
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

    public void updateStock(@PathVariable("id") String id, @RequestBody Stock stock) {
        stockService.updateStock(id, stock);
    }

    // Logical Delete
    @PutMapping(path = "delete/{id}")
    @Operation(
            description = "Logically delete stock by id",
            summary = "Logically delete stocks by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })

    public void logicallyDeleteStock(@PathVariable("id") String id, @RequestBody Stock stock){
        stockService.logicallyDeleteStock(id, stock);
    }

    // Get stock by stock collection
    @GetMapping(path = "collection/{name}")
    @Operation(
            description = "Get stock by stock collection name",
            summary = "Retrieve stocks by using the stock collection name",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })

    public List<Stock> getStockByCollectionName(@PathVariable("name") String stockCollectionName) {
        return stockService.getStockByCollectionName(stockCollectionName);
    }
}

