package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.lafresca.lafrescabackend.Models.FoodItem;
import org.lafresca.lafrescabackend.Services.FoodItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/lafresca/food")
@AllArgsConstructor
@Tag(name="Food Item Controller")
public class FoodItemController {
    private final FoodItemService foodItemService;

    // Add new food item
    @PostMapping
    @Operation(
            description = "Add new food item",
            summary = "Add new food item to the branch",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public String addNewFood(@RequestBody FoodItem foodItem) {
        return foodItemService.addNewFood(foodItem);
    }

    // Retrieve all food items
    @GetMapping
    @Operation(
            description = "Get all food items",
            summary = "Retrieve all food items in the branch",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public List<FoodItem> getFoods(){
        return foodItemService.getFoods();
    }

    // Search food item
    @GetMapping(path = "{id}")
    @Operation(
            description = "Search food item by id",
            summary = "Retrieve food items by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public Optional<FoodItem> getFood(@PathVariable("id") String id){
        return foodItemService.getFood(id);
    }

    // Delete food item
    @DeleteMapping(path = "{id}")
    @Operation(
            description = "Delete food item by id",
            summary = "Delete food items by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public void deleteFood(@PathVariable("id") String id){
        foodItemService.deleteFood(id);
    }

    // Update food item
    @PutMapping(path = "{id}")
    @Operation(
            description = "Update food item by id",
            summary = "Update food items by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public void updateFood(@PathVariable("id") String id, @RequestBody FoodItem foodItem){
        foodItemService.updateFood(id, foodItem);
    }
}
