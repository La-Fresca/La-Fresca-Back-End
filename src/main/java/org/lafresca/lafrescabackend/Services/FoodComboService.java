package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.FoodCombo;
import org.lafresca.lafrescabackend.Models.FoodItem;
import org.lafresca.lafrescabackend.Repositories.FoodComboRepository;
import org.lafresca.lafrescabackend.Repositories.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodComboService {
    private final FoodComboRepository foodComboRepository;
    private final FoodItemRepository foodItemRepository;

    @Autowired
    public FoodComboService(FoodComboRepository foodComboRepository, FoodItemRepository foodItemRepository) {
        this.foodComboRepository = foodComboRepository;
        this.foodItemRepository = foodItemRepository;
    }

    // Add new food combo
    public String addNewFoodCombo(FoodCombo foodCombo) {
        String error = null;
        LocalDate now = LocalDate.now();

        if (foodCombo.getName() == null || foodCombo.getName().isEmpty()) {
            error = "Please enter name";
        }
        else if (foodCombo.getDescription() == null || foodCombo.getDescription().isEmpty()) {
            error = "Please enter description";
        }
        else if (foodCombo.getPrice() <= 0) {
            error = "Please enter a valid price";
        }
        else if (foodCombo.getImage() == null || foodCombo.getImage().isEmpty()) {
            error = "Please upload image";
        }
        else if (foodCombo.getCafeId() == null || foodCombo.getCafeId().isEmpty()) {
            error = "Please enter cafe id";
        }
        else if (foodCombo.getDiscountStatus() == null) {
            foodCombo.setDiscountStatus(0);
        }
        else if (foodCombo.getAvailable() < 0 || foodCombo.getAvailable() > 1) {
            error = "Invalid value for availability";
        }
        else if (foodCombo.getDeleted() < 0 || foodCombo.getDeleted() > 1) {
            error = "Invalid value for deleted status";
        }
        else if (foodCombo.getFoodIds() == null || foodCombo.getFoodIds().isEmpty()) {
            error = "Please enter at least one food ID";
        }

        if (error == null) {
            foodCombo.setDeleted(0);
            foodCombo.setPostedDate(now);
            foodCombo.setWeeklySellingCount(0);
            foodCombo.setTotalSellingCount(0);
            foodComboRepository.save(foodCombo);
        }
        return error;
    }

    // Retrieve all food combos
    public List<FoodCombo> getFoodCombos(String cafeId) {
        List<FoodCombo> foodComboList = foodComboRepository.findByCafeId(cafeId, 0);

        for (FoodCombo foodCombo : foodComboList) {
            List<String> foodNames = new ArrayList<>();
            for (String foodId : foodCombo.getFoodIds()) {
                FoodItem foodItem = foodItemRepository.findOneById(foodId);
                foodNames.add(foodItem.getName());
            }

            foodCombo.setFoodNames(foodNames);
        }

        return foodComboList;
    }

    // Update food combo
    public void updateFoodCombo(String id, FoodCombo foodCombo) {
        FoodCombo existingFoodCombo = foodComboRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Combo not found with id " + id));
        LocalDateTime now = LocalDateTime.now();

        if (foodCombo.getName() != null && !foodCombo.getName().isEmpty()) {
            existingFoodCombo.setName(foodCombo.getName());
        }
        if (foodCombo.getDescription() != null && !foodCombo.getDescription().isEmpty()) {
            existingFoodCombo.setDescription(foodCombo.getDescription());
        }
        if (foodCombo.getPrice() > 0) {
            existingFoodCombo.setPrice(foodCombo.getPrice());
        }
        if (foodCombo.getImage() != null & !foodCombo.getImage().isEmpty()) {
            existingFoodCombo.setImage(foodCombo.getImage());
        }
        if (foodCombo.getCafeId() != null && !foodCombo.getCafeId().isEmpty()) {
            existingFoodCombo.setCafeId(foodCombo.getCafeId());
        }
        if (foodCombo.getAvailable() == 0 || foodCombo.getAvailable() == 1) {
            existingFoodCombo.setAvailable(foodCombo.getAvailable());
        }
        if (foodCombo.getDiscountStatus() == 0 || foodCombo.getDiscountStatus() == 1) {
            existingFoodCombo.setDiscountStatus(foodCombo.getDiscountStatus());
        }
        if (foodCombo.getRating() != null) {
            existingFoodCombo.setRating(foodCombo.getRating());
        }

        foodComboRepository.save(existingFoodCombo);
    }

    // Delete food combo item by id
    public void deleteFoodCombo(String id) {
        foodComboRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Food Combo not found with id " + id));
        foodComboRepository.deleteById(id);
    }

    // Search food combo
    public Optional<FoodCombo> getFoodCombo(String id) {
        foodComboRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Food Combo not found with id " + id));
        return foodComboRepository.findById(id);
    }

    // Logical Delete
    public void logicallyDeleteFoodCombo(String id, FoodCombo foodCombo) {
        FoodCombo existingFoodCombo = foodComboRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Food Combo not found with id " + id));

        existingFoodCombo.setDeleted(foodCombo.getDeleted());

        foodComboRepository.save(existingFoodCombo);
    }
}
