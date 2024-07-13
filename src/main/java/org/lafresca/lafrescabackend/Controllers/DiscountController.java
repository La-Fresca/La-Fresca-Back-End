package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.lafresca.lafrescabackend.Models.Discount;
import org.lafresca.lafrescabackend.Services.DiscountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/lafresca/discount")
@AllArgsConstructor
@Tag(name = "Discount Controller")
public class DiscountController {
    private final DiscountService discountService;

    // Add new discount
    @PostMapping
    @Operation(
            description = "Add new discount",
            summary = "Add new discount to branch",
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

    public String addDiscount(@RequestBody Discount discount) { return discountService.addDiscount(discount); }

    // Retrieve all discounts
    @GetMapping
    @Operation(
            description = "Get all discounts",
            summary = "Retrieve all discounts in the branch",
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

    public List<Discount> getDiscounts() { return discountService.getDiscounts(); }

    // Search discount
    @GetMapping(path = "{id}")
    @Operation(
            description = "Search discount by id",
            summary = "Retrieve discount by using the id",
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

    public Optional<Discount> getDiscount(@PathVariable("id") String id) { return discountService.getDiscount(id);}
}
