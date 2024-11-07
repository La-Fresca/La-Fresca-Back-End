package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lafresca.lafrescabackend.DTO.FoodComboDTO;
import org.lafresca.lafrescabackend.DTO.Request.FoodComboRequestDTO;
import org.lafresca.lafrescabackend.DTO.Request.FoodItemRequestDTO;
import org.lafresca.lafrescabackend.Models.FoodCombo;
import org.lafresca.lafrescabackend.Models.FoodItem;
import org.lafresca.lafrescabackend.Services.FoodComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "api/lafresca/foodCombo")
@Validated
@Slf4j
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
    public ResponseEntity<FoodComboRequestDTO> addNewFoodCombo(@Valid @RequestBody FoodComboRequestDTO foodCombo) {
        return ResponseEntity.status(201).body(foodComboService.addNewFoodCombo(foodCombo));
    }

    // Retrieve all food combos
    @GetMapping(path = "getAll/{cafeId}")
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
    public List<FoodComboDTO> getFoodCombos(@PathVariable("cafeId") String cafeId){
        return foodComboService.getFoodCombos(cafeId);
    }

    // Retrieve all food combos for top-level manager
    @GetMapping(path = "getAllForTLM")
    @Operation(
            description = "Get all food combos for top level manager",
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
    public List<FoodCombo> getFoodItemsForTLM() {
        return foodComboService.getFoodCombosForTLM();
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

    // Change availability
    @PutMapping(path = "availability/{id}")
    @Operation(
            description = "Change availability of food combo by id",
            summary = "Change availability of food combos by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public ResponseEntity<FoodCombo> changeAvailability(@PathVariable("id") String id, @RequestBody Integer value){
        return ResponseEntity.status(201).body(foodComboService.changeAvailability(id, value));
    }

    // Approve Food Combo
    @PutMapping(path = "approve/{id}")
    @Operation(
            description = "Approve food combo by id",
            summary = "Approve food combos by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public ResponseEntity<FoodCombo> approveComboItem(@PathVariable("id") String id){
        return ResponseEntity.status(201).body(foodComboService.approveFoodCombo(id));
    }

    // Reject Food Combo
    @PutMapping(path = "reject/{id}")
    @Operation(
            description = "Reject food combo by id",
            summary = "Reject food combos by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public ResponseEntity<FoodCombo> rejectComboItem(@PathVariable("id") String id){
        return ResponseEntity.status(201).body(foodComboService.rejectFoodCombo(id));
    }
}
