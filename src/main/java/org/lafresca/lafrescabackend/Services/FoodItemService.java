package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.DTO.FoodItemDTO;
import org.lafresca.lafrescabackend.DTO.FoodItemDTOMapper;
import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.FoodItem;
import org.lafresca.lafrescabackend.Repositories.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        LocalDateTime now = LocalDateTime.now();

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
        else if (foodItem.getDeleted() < 0 || foodItem.getDeleted() > 1) {
            error = "Invalid value for discount status";
        }
        else if (foodItem.getFeatures() == null || foodItem.getFeatures().isEmpty()) {
            error = "Please enter at least one feature";
        }
        else if (foodItem.getDiscountDetails().getName() == null || foodItem.getDiscountDetails().getName().isEmpty()) {
            error = "Name cannot be empty";
        }
        else if (foodItem.getDiscountDetails().getDescription() == null || foodItem.getDiscountDetails().getDescription().isEmpty()) {
            error = "Description cannot be empty";
        }
        else if (foodItem.getDiscountDetails().getDiscountType() == null || foodItem.getDiscountDetails().getDiscountType().isEmpty()) {
            error = "Discount type cannot be empty";
        }
        else if (!(foodItem.getDiscountDetails().getDiscountType().equals("Price Offer")) && !(foodItem.getDiscountDetails().getDiscountType().equals("Promotional Offer"))) {
            error = "Discount type not valid";
        }
        else if (foodItem.getDiscountDetails().getDiscountAmount() < 0 ) {
            error = "Discount amount not valid";
        }
        else if (foodItem.getDiscountDetails().getStartDate() == null || foodItem.getDiscountDetails().getStartDate().toString().isEmpty()) {
            error = "Start date not valid";
        }
        else if (LocalDateTime.parse(foodItem.getDiscountDetails().getStartDate().toString()).isBefore(now)) {
            error = "Start date not valid";
        }
        else if (foodItem.getDiscountDetails().getEndDate() == null || foodItem.getDiscountDetails().getEndDate().toString().isEmpty()) {
            error = "End date not valid";
        }
        else if (LocalDateTime.parse(foodItem.getDiscountDetails().getEndDate().toString()).isBefore(LocalDateTime.parse(foodItem.getDiscountDetails().getStartDate().toString()))) {
            error = "End date not valid";
        }
        else if (foodItem.getDiscountDetails().getCafeId() == null || foodItem.getDiscountDetails().getCafeId().isEmpty()) {
            error = "Cafe Id cannot be empty";
        }
        else if (foodItem.getDiscountDetails().getIsActive() < 0 || foodItem.getDiscountDetails().getIsActive() > 1) {
            error = "Discount availability value not valid";
        }
        else if (foodItem.getDiscountDetails().getMenuItemId() == null || foodItem.getDiscountDetails().getMenuItemId().isEmpty()) {
            error = "MenuItem Id cannot be empty";
        }
        else if (foodItem.getDiscountDetails().getMenuItemType() == null || foodItem.getDiscountDetails().getMenuItemType().isEmpty()) {
            error = "MenuItem type cannot be empty";
        }
        else if (!(foodItem.getDiscountDetails().getMenuItemType().equals("Food Item"))) {
            error = "MenuItem type not valid";
        }
        else if (foodItem.getDiscountDetails().getOfferDetails() == null || foodItem.getDiscountDetails().getOfferDetails().isEmpty()) {
            error = "Offer details cannot be empty";
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
        LocalDateTime now = LocalDateTime.now();

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
        if (foodItem.getRating() != 0) {
            existingFoodItem.setRating(foodItem.getRating());
        }
        if (foodItem.getDiscountDetails().getName() != null && !foodItem.getDiscountDetails().getName().isEmpty()) {
            existingFoodItem.getDiscountDetails().setName(foodItem.getDiscountDetails().getName());
        }
        if (foodItem.getDiscountDetails().getDescription() != null && !foodItem.getDiscountDetails().getDescription().isEmpty()) {
            existingFoodItem.getDiscountDetails().setDescription(foodItem.getDiscountDetails().getDescription());
        }
        if (foodItem.getDiscountDetails().getDiscountType().equals("Price Offer") || foodItem.getDiscountDetails().getDiscountType().equals("Promotional Offer")) {
            existingFoodItem.getDiscountDetails().setDiscountType(foodItem.getDiscountDetails().getDiscountType());
        }
        if (foodItem.getDiscountDetails().getDiscountAmount() > 0) {
            existingFoodItem.getDiscountDetails().setDiscountAmount(foodItem.getDiscountDetails().getDiscountAmount());
        }
        if (foodItem.getDiscountDetails().getStartDate() != null && !foodItem.getDiscountDetails().getStartDate().toString().isEmpty() && !LocalDateTime.parse(foodItem.getDiscountDetails().getStartDate().toString()).isBefore(now)) {
            existingFoodItem.getDiscountDetails().setStartDate(foodItem.getDiscountDetails().getStartDate());
        }
        if (foodItem.getDiscountDetails().getEndDate() != null && !foodItem.getDiscountDetails().getEndDate().toString().isEmpty() && !LocalDateTime.parse(foodItem.getDiscountDetails().getEndDate().toString()).isBefore(LocalDateTime.parse(foodItem.getDiscountDetails().getStartDate().toString()))) {
            existingFoodItem.getDiscountDetails().setEndDate(foodItem.getDiscountDetails().getEndDate());
        }
        if (foodItem.getDiscountDetails().getCafeId() != null && !foodItem.getDiscountDetails().getCafeId().isEmpty()) {
            existingFoodItem.getDiscountDetails().setCafeId(foodItem.getDiscountDetails().getCafeId());
        }
        if (foodItem.getDiscountDetails().getIsActive() == 0 || foodItem.getDiscountDetails().getIsActive() == 1) {
            existingFoodItem.getDiscountDetails().setIsActive(foodItem.getDiscountDetails().getIsActive());
        }
        if (foodItem.getDiscountDetails().getMenuItemId() != null && !foodItem.getDiscountDetails().getMenuItemId().isEmpty()) {
            existingFoodItem.getDiscountDetails().setMenuItemId(existingFoodItem.getDiscountDetails().getMenuItemId());
        }
        if (foodItem.getDiscountDetails().getMenuItemType().equals("Food Item")) {
            existingFoodItem.getDiscountDetails().setMenuItemType(existingFoodItem.getDiscountDetails().getMenuItemType());
        }
        if (foodItem.getDiscountDetails().getOfferDetails() != null && !foodItem.getDiscountDetails().getOfferDetails().isEmpty()) {
            existingFoodItem.getDiscountDetails().setOfferDetails(foodItem.getDiscountDetails().getOfferDetails());
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
        FoodItem existingFood = foodItemRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("FoodItem not found with id " + id));

        existingFood.setDeleted(foodItem.getDeleted());

        foodItemRepository.save(existingFood);
    }
}
