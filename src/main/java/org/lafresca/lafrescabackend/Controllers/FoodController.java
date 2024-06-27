package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.lafresca.lafrescabackend.Models.Food;
import org.lafresca.lafrescabackend.Services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/lafresca/food")
@AllArgsConstructor
@Tag(name="Food Controller")
public class FoodController {
    private final FoodService foodService;

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
    public String addNewFood(@RequestBody Food food) {
        return foodService.addNewFood(food);
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
    public List<Food> getFoods(){
        return foodService.getFoods();
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
    public Optional<Food> getFood(@PathVariable("id") String id){
        return foodService.getFood(id);
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
        foodService.deleteFood(id);
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
    public void updateFood(@PathVariable("id") String id, @RequestBody Food food){
        foodService.updateFood(id, food);
    }
}
