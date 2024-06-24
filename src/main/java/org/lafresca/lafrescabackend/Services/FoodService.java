package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Models.Food;
import org.lafresca.lafrescabackend.Repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public void addNewFood(Food food) {
        foodRepository.save(food);
    }

    public List<Food> getFoods() {
        return foodRepository.findAll();
    }
}
