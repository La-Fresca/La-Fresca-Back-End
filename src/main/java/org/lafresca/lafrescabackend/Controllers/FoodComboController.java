package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.lafresca.lafrescabackend.Models.FoodCombo;
import org.lafresca.lafrescabackend.Services.FoodComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "api/lafresca/foodCombo")
@Tag(name="Food Combo Controller")
public class FoodComboController {
    private final FoodComboService foodComboService;

    @Autowired
    public FoodComboController(FoodComboService foodComboService) {
        this.foodComboService = foodComboService;
    }

    // Add new food combo
    @PostMapping
    @Operation(
            description = "Add new food combo",
            summary = "Add new food combo to the branch",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public String addNewFoodCombo(@RequestBody FoodCombo foodCombo) {
        return foodComboService.addNewFoodCombo(foodCombo);
    }

    // Retrieve all food combos
    @GetMapping
    @Operation(
            description = "Get all food combos",
            summary = "Retrieve all food combos in the branch",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public List<FoodCombo> getFoodCombos(){
        return foodComboService.getFoodCombos();
    }

    // Search food combo
    @GetMapping(path = "{id}")
    @Operation(
            description = "Search food combo by id",
            summary = "Retrieve food combos by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public Optional<FoodCombo> getFoodCombo(@PathVariable("id") String id){
        return foodComboService.getFoodCombo(id);
    }

    // Delete food combo
    @DeleteMapping(path = "{id}")
    @Operation(
            description = "Delete food combo by id",
            summary = "Delete food combos by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public void deleteFoodCombo(@PathVariable("id") String id){
        foodComboService.deleteFoodCombo(id);
    }

    // Update food combo
    @PutMapping(path = "{id}")
    @Operation(
            description = "Update food combo by id",
            summary = "Update food combos by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public void updateFoodCombo(@PathVariable("id") String id, @RequestBody FoodCombo foodCombo){
        foodComboService.updateFoodCombo(id, foodCombo);
    }

    // Logical Delete
    @PutMapping(path = "delete/{id}")
    @Operation(
            description = "Logically delete food combo by id",
            summary = "Logically delete food combos by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })

    public void logicallyDeleteFoodCombo(@PathVariable("id") String id, @RequestBody FoodCombo foodCombo){
        foodComboService.logicallyDeleteFoodCombo(id, foodCombo);
    }
}
