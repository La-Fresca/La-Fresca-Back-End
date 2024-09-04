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
@CrossOrigin
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

    public String addNewDiscount(@RequestBody Discount discount) { return discountService.addNewDiscount(discount); }

    // Retrieve all discounts
    @GetMapping(path = "{cafeId}")
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

    public List<List<Discount>> getDiscounts(@PathVariable("cafeId") String cafeId) { return discountService.getDiscounts(cafeId); }

    // Search discount
//    @GetMapping(path = "{id}")
//    @Operation(
//            description = "Search discount by id",
//            summary = "Retrieve discount by using the id",
//            responses = {
//                    @ApiResponse(
//                            description = "Success",
//                            responseCode = "200"
//                    ),
//                    @ApiResponse(
//                            description = "Unauthorized / Invalid Token",
//                            responseCode = "403"
//                    )
//            })
//
//    public Optional<Discount> getDiscount(@PathVariable("id") String id) { return discountService.getDiscount(id);}

    // Delete discount
//    @DeleteMapping(path = "{id}")
//    @Operation(
//            description = "Delete discount by id",
//            summary = "Delete discount by using the id",
//            responses = {
//                    @ApiResponse(
//                            description = "Success",
//                            responseCode = "200"
//                    ),
//                    @ApiResponse(
//                            description = "Unauthorized / Invalid Token",
//                            responseCode = "403"
//                    )
//            })
//
//    public void deleteDiscount(@PathVariable("id") String id) { discountService.deleteDiscount(id); }

    // Update discount
//    @PutMapping(path = "{id}")
//    @Operation(
//            description = "Update discount by id",
//            summary = "Update discount by using the id",
//            responses = {
//                    @ApiResponse(
//                            description = "Success",
//                            responseCode = "200"
//                    ),
//                    @ApiResponse(
//                            description = "Unauthorized / Invalid Token",
//                            responseCode = "403"
//                    )
//            })
//
//    public void updateDiscount(@PathVariable("id") String id, @RequestBody Discount discount) {
//        discountService.updateDiscount(id, discount);
//    }
}
