package org.lafresca.lafrescabackend.Controllers;

import org.lafresca.lafrescabackend.Models.Food;
import org.lafresca.lafrescabackend.Models.User;
import org.lafresca.lafrescabackend.Services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/lafresca/food")
public class FoodController {
    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    public void registerNewUser(@RequestBody Food food) {
        foodService.addNewFood(food);
    }
}
