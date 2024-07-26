package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.DTO.FoodItemDTO;
import org.lafresca.lafrescabackend.DTO.FoodItemDTOMapper;
import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.FoodItem;
import org.lafresca.lafrescabackend.Repositories.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodItemService {
    private final FoodItemRepository foodItemRepository;
    private final FoodItemDTOMapper foodItemDTOMapper;

    @Autowired
    public FoodItemService(FoodItemRepository foodItemRepository, FoodItemDTOMapper foodItemDTOMapper) {
        this.foodItemRepository = foodItemRepository;
        this.foodItemDTOMapper = foodItemDTOMapper;
    }

    // Add new food item
    public String addNewFoodItem(FoodItem foodItem) {
        String error = null;

        if (foodItem.getName() == null || foodItem.getName().isEmpty()) {
            error = "Please enter name";
        }
        else if (foodItem.getDescription() == null || foodItem.getDescription().isEmpty()) {
            error = "Please enter description";
        }
        else if (foodItem.getPrice() <= 0) {
            error = "Please enter a valid price";
        }
        else if (foodItem.getImage() == null || foodItem.getImage().isEmpty()) {
            error = "Please upload image";
        }
        else if (foodItem.getCafeId() == null || foodItem.getCafeId().isEmpty()) {
            error = "Please enter cafe id";
        }
        else if (foodItem.getDiscountStatus() == null) {
            foodItem.setDiscountStatus(0);
        }
        else if (foodItem.getAvailable() < 0 || foodItem.getAvailable() > 1) {
            error = "Invalid value for availability";
        }
        else if (foodItem.getDiscountStatus() < 0 || foodItem.getDiscountStatus() > 1) {
            error = "Invalid value for deleted status";
        }
        else if (foodItem.getDiscountStatus() == 1 && (foodItem.getDiscountID() != null || foodItem.getDiscountID().isEmpty())) {
            error = "Invalid value for discount id";
        }
        else if (foodItem.getDeleted() < 0 || foodItem.getDeleted() > 1) {
            error = "Invalid value for discount status";
        }
        else if (foodItem.getFeatures() == null || foodItem.getFeatures().isEmpty()) {
            error = "Please enter at least one feature";
        }

        if (error == null) {
            foodItem.setDeleted(0);
            foodItem.setDiscountStatus(0);
            foodItemRepository.save(foodItem);
        }
        return error;
    }

    // Retrieve all food items
    public List<FoodItemDTO> getFoodItems() {
        return foodItemRepository.findByDeleted(0)
                .stream()
                .map(foodItemDTOMapper)
                .collect(Collectors.toList());
    }

    // Update food item
    public void updateFoodItem(String id, FoodItem foodItem) {
        FoodItem existingFoodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FoodItem not found with id " + id));

        if (foodItem.getName() != null && !foodItem.getName().isEmpty()) {
            existingFoodItem.setName(foodItem.getName());
        }
        if (foodItem.getDescription() != null && !foodItem.getDescription().isEmpty()) {
            existingFoodItem.setDescription(foodItem.getDescription());
        }
        if (foodItem.getPrice() != null && foodItem.getPrice() >= 0) {
            existingFoodItem.setPrice(foodItem.getPrice());
        }
        if (foodItem.getImage() != null && !foodItem.getImage().isEmpty()) {
            existingFoodItem.setImage(foodItem.getImage());
        }
        if (foodItem.getCafeId() != null && !foodItem.getCafeId().isEmpty()) {
            existingFoodItem.setCafeId(foodItem.getCafeId());
        }
        if (foodItem.getAvailable() == 0 || foodItem.getAvailable() == 1) {
            existingFoodItem.setAvailable(foodItem.getAvailable());
        }
        if (foodItem.getDeleted() == 0 || foodItem.getDeleted() == 1) {
            existingFoodItem.setDeleted(foodItem.getDeleted());
        }
        if (foodItem.getDiscountStatus() == 0 || foodItem.getDiscountStatus() == 1) {
            existingFoodItem.setDiscountStatus(foodItem.getDiscountStatus());
        }
        if (foodItem.getRating() != 0){
            existingFoodItem.setRating(foodItem.getRating());
        }

        existingFoodItem.setCategories(foodItem.getCategories());

        existingFoodItem.setFeatures(foodItem.getFeatures());

        foodItemRepository.save(existingFoodItem);
    }

    // Delete food item by id
    public void deleteFoodItem(String id) {
        foodItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FoodItem not found with id " + id));
        foodItemRepository.deleteById(id);
    }

    // Search food item
    public Optional<FoodItem> getFoodItem(String id) {
        foodItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FoodItem not found with id " + id));
        return foodItemRepository.findById(id);
    }
}
