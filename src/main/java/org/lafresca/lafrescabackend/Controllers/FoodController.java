package org.lafresca.lafrescabackend.Controllers;

import org.lafresca.lafrescabackend.Models.Food;
import org.lafresca.lafrescabackend.Services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/lafresca/food")
public class FoodController {
    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    // Add new food item
    @PostMapping
    public void addNewFood(@RequestBody Food food) {
        foodService.addNewFood(food);
    }

    // Retrieve all food items
    @GetMapping
    public List<Food> getFoods(){
        return foodService.getFoods();
    }

    // Search food item
    @GetMapping(path = "{id}")
    public Optional<Food> getFood(@PathVariable("id") String id){
        return foodService.getFood(id);
    }

    // Delete food item
    @DeleteMapping(path = "{id}")
    public void deleteFood(@PathVariable("id") String id){
        foodService.deleteFood(id);
    }

    // Update food item
    @PutMapping(path = "{id}")
    public void updateFood(@PathVariable("id") String id, @RequestBody Food food){
        foodService.updateFood(id, food);
    }
}
