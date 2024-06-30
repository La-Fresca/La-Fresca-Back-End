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
    public String addNewMenu(@RequestBody FoodCombo foodCombo) {
        return foodComboService.addNewMenu(foodCombo);
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
    public List<FoodCombo> getMenus(){
        return foodComboService.getMenus();
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
    public Optional<FoodCombo> getMenu(@PathVariable("id") String id){
        return foodComboService.getMenu(id);
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
    public void deleteMenu(@PathVariable("id") String id){
        foodComboService.deleteMenu(id);
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
    public void updateMenu(@PathVariable("id") String id, @RequestBody FoodCombo foodCombo){
        foodComboService.updateMenu(id, foodCombo);
    }
}
