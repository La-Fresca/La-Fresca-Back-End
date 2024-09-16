package org.lafresca.lafrescabackend.Services;

import jakarta.validation.Valid;
import org.lafresca.lafrescabackend.DTO.FoodItemDTO;
import org.lafresca.lafrescabackend.DTO.FoodItemDTOMapper;
import org.lafresca.lafrescabackend.DTO.Request.FoodItemRequestDTO;
import org.lafresca.lafrescabackend.DTO.cafeDTO;
import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.FoodItem;
import org.lafresca.lafrescabackend.Repositories.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public FoodItemRequestDTO addNewFoodItem(@Valid FoodItemRequestDTO foodItem) {
        LocalDate now = LocalDate.now();

        FoodItem newFoodItem = new FoodItem();
        newFoodItem.setName(foodItem.getName());
        newFoodItem.setDescription(foodItem.getDescription());
        newFoodItem.setPrice(foodItem.getPrice());
        newFoodItem.setImage(foodItem.getImage());
        newFoodItem.setFeatures(foodItem.getFeatures());
        newFoodItem.setCategories(foodItem.getCategories());

        newFoodItem.setDeleted(0);
        newFoodItem.setRating(0.0);
        newFoodItem.setRatingCount(0);
        newFoodItem.setPostedDate(now);
        newFoodItem.setWeeklySellingCount(0);
        newFoodItem.setTotalSellingCount(0);
        newFoodItem.setDiscountStatus(0);


        foodItemRepository.save(newFoodItem);
        return foodItem;
    }

    // Retrieve all food items
    public List<FoodItemDTO> getFoodItems(String cafeId) {
        return foodItemRepository.findByCafeId(cafeId, 0)
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
        if (foodItem.getCost() != null && foodItem.getCost() > 0) {
            existingFoodItem.setCost(foodItem.getCost());
        }
        if (foodItem.getDiscountStatus() != null && (foodItem.getDiscountStatus() == 0 || foodItem.getDiscountStatus() == 1)) {
            existingFoodItem.setDiscountStatus(foodItem.getDiscountStatus());
        }
        if (foodItem.getRating() != null && foodItem.getRating() != 0){
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

    // Logical Delete
    public void logicallyDeleteFoodItem(String id, FoodItem foodItem) {
        FoodItem existingFoodItem = foodItemRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("FoodItem not found with id " + id));

        existingFoodItem.setDeleted(foodItem.getDeleted());

        foodItemRepository.save(existingFoodItem);
    }

    public cafeDTO createCafe(@Valid cafeDTO restaurantDto) {
        FoodItem foodItem = new FoodItem();
        foodItem.setName(restaurantDto.getName());
        foodItemRepository.save(foodItem);

        return restaurantDto;
    }
}
