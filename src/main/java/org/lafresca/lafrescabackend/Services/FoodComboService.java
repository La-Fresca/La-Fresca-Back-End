package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.FoodCombo;
import org.lafresca.lafrescabackend.Repositories.FoodComboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodComboService {
    private final FoodComboRepository foodComboRepository;

    @Autowired
    public FoodComboService(FoodComboRepository foodComboRepository) {
        this.foodComboRepository = foodComboRepository;
    }

    // Add new food combo
    public String addNewMenu(FoodCombo foodCombo) {
        String error = null;

        if (foodCombo.getName() == null || foodCombo.getName().isEmpty()) {
            error = "Please enter name";
        } else if (foodCombo.getDescription() == null || foodCombo.getDescription().isEmpty()) {
            error = "Please enter description";
        } else if (foodCombo.getPrice() <= 0) {
            error = "Please enter a valid price";
        } else if (foodCombo.getImage() == null || foodCombo.getImage().isEmpty()) {
            error = "Please upload image";
        } else if (foodCombo.getCafeId() == null || foodCombo.getCafeId().isEmpty()) {
            error = "Please enter cafe id";
        } else if (foodCombo.getAvailable() < 0 || foodCombo.getAvailable() > 1) {
            error = "Invalid value for availability";
        } else if (foodCombo.getDeleted() < 0 || foodCombo.getDeleted() > 1) {
            error = "Invalid value for deleted status";
        } else if (foodCombo.getFoodIds() == null || foodCombo.getFoodIds().isEmpty()) {
            error = "Please enter at least one food ID";
        }

        if (error == null) {
            foodComboRepository.save(foodCombo);
        }
        return error;
    }


    // Retrieve all food combos
    public List<FoodCombo> getMenus() {
        return foodComboRepository.findAll();
    }

    // Update food combo
    public void updateMenu(String id, FoodCombo foodCombo) {
        FoodCombo existingFoodCombo = foodComboRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu not found with id " + id));

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
        if (foodCombo.getAvailable() != 0 && foodCombo.getAvailable() != 1) {
            existingFoodCombo.setAvailable(foodCombo.getAvailable());
        }
        if (foodCombo.getDeleted() != 0 && foodCombo.getDeleted() != 1) {
            existingFoodCombo.setDeleted(foodCombo.getDeleted());
        }

        foodComboRepository.save(existingFoodCombo);
    }

    // Delete food combo item by id
    public void deleteMenu(String id) {
        foodComboRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Menu not found with id " + id));
        foodComboRepository.deleteById(id);
    }

    // Search food combo
    public Optional<FoodCombo> getMenu(String id) {
        foodComboRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Menu not found with id " + id));
        return foodComboRepository.findById(id);
    }
}
