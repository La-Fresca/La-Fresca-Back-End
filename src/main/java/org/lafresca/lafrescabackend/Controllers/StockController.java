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
}

