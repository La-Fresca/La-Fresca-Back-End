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
        if (Objects.equals(food.getName(), "")){
            error = "Please enter name";
        }
        else if (Objects.equals(food.getDescription(), "")){
            error = "Please enter description";
        }
        else if(food.getPrice() == 0){
            error = "Please enter price";
        }
        else if (Objects.equals(food.getImage(), "")){
            error = "Please upload image";
        }
        else if(Objects.equals(food.getCafeId(), "")){
            error = "Please enter cafe id";
        }

        if (error == null){
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
        existingFood.setName(food.getName());
        existingFood.setDescription(food.getDescription());
        existingFood.setPrice(food.getPrice());
        existingFood.setImage(food.getImage());
        existingFood.setAvailable(food.getAvailable());
        existingFood.setFeatures(food.getFeatures());
        existingFood.setCafeId(food.getCafeId());
        existingFood.setDeleted(food.getDeleted());
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
