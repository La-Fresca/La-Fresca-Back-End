package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lafresca.lafrescabackend.DTO.FoodItemDTO;
import org.lafresca.lafrescabackend.DTO.Request.FoodItemRequestDTO;
import org.lafresca.lafrescabackend.DTO.cafeDTO;
import org.lafresca.lafrescabackend.Models.FoodItem;
import org.lafresca.lafrescabackend.Services.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "api/lafresca/foodItem")
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name="Food Item Controller")
public class FoodItemController {
    private final FoodItemService foodItemService;

    @PostMapping("/addCafe")
    public ResponseEntity<cafeDTO> addRestaurant(@Valid @RequestBody cafeDTO restaurantDto) {
        return ResponseEntity.status(201).body(foodItemService.createCafe(restaurantDto));
    }

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
    public ResponseEntity<FoodItemRequestDTO> addNewFoodItem(@Valid @RequestBody FoodItemRequestDTO foodItem) {
        return ResponseEntity.status(201).body(foodItemService.addNewFoodItem(foodItem));
    }

    // Retrieve all food items
    @GetMapping(path = "getAll/{cafeId}")
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
    public List<FoodItemDTO> getFoodItems(@PathVariable("cafeId") String cafeId) {
        return foodItemService.getFoodItems(cafeId);
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
    public Optional<FoodItem> getFoodItem(@PathVariable("id") String id){
        return foodItemService.getFoodItem(id);
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
    public void deleteFoodItem(@PathVariable("id") String id){
        foodItemService.deleteFoodItem(id);
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
    public void updateFoodItem(@PathVariable("id") String id, @RequestBody FoodItem foodItem){
        foodItemService.updateFoodItem(id, foodItem);
    }

    // Logical Delete
    @PutMapping(path = "delete/{id}")
    @Operation(
            description = "Logically delete food item by id",
            summary = "Logically delete food items by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })

    public void logicallyDeleteFoodItem(@PathVariable("id") String id, @RequestBody FoodItem foodItem){
        foodItemService.logicallyDeleteFoodItem(id, foodItem);
    }
}
