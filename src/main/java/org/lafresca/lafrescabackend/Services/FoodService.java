package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.Food;
import org.lafresca.lafrescabackend.Repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    // Add new food item
    public String addNewFood(Food food) {
        String error = null;

        if (food.getName() == null || food.getName().isEmpty()) {
            error = "Please enter name";
        } else if (food.getDescription() == null || food.getDescription().isEmpty()) {
            error = "Please enter description";
        } else if (food.getPrice() <= 0) {
            error = "Please enter a valid price";
        } else if (food.getImage() == null || food.getImage().isEmpty()) {
            error = "Please upload image";
        } else if (food.getCafeId() == null || food.getCafeId().isEmpty()) {
            error = "Please enter cafe id";
        } else if (food.getAvailable() < 0 || food.getAvailable() > 1) {
            error = "Invalid value for availability";
        } else if (food.getDeleted() < 0 || food.getDeleted() > 1) {
            error = "Invalid value for deleted status";
        } else if (food.getFeatures() == null || food.getFeatures().isEmpty()) {
            error = "Please enter at least one feature";
        }

        if (error == null) {
            foodRepository.save(food);
        }
        return error;
    }


    // Retrieve all food items
    public List<Food> getFoods() {
        return foodRepository.findAll();
    }

    // Update food item
    public void updateFood(String id, Food food) {
        Food existingFood = foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found with id " + id));

        if (food.getName() != null && !food.getName().isEmpty()) {
            existingFood.setName(food.getName());
        }
        if (food.getDescription() != null && !food.getDescription().isEmpty()) {
            existingFood.setDescription(food.getDescription());
        }
        if (food.getPrice() != null && food.getPrice() >= 0) {
            existingFood.setPrice(food.getPrice());
        }
        if (food.getImage() != null && !food.getImage().isEmpty()) {
            existingFood.setImage(food.getImage());
        }
        if (food.getCafeId() != null && !food.getCafeId().isEmpty()) {
            existingFood.setCafeId(food.getCafeId());
        }
        if (food.getAvailable() == 0 || food.getAvailable() == 1) {
            existingFood.setAvailable(food.getAvailable());
        }
        if (food.getDeleted() == 0 || food.getDeleted() == 1) {
            existingFood.setDeleted(food.getDeleted());
        }

        existingFood.setFeatures(food.getFeatures());

        foodRepository.save(existingFood);
    }

    // Delete food item by id
    public void deleteFood(String id) {
        foodRepository.deleteById(id);
    }

    // Search food item
    public Optional<Food> getFood(String id) {
        return foodRepository.findById(id);
    }
}
