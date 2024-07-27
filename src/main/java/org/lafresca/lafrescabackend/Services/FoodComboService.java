package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.FoodCombo;
import org.lafresca.lafrescabackend.Repositories.FoodComboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public String addNewFoodCombo(FoodCombo foodCombo) {
        String error = null;
        LocalDateTime now = LocalDateTime.now();

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
        else if (foodCombo.getDiscountDetails().getName() == null || foodCombo.getDiscountDetails().getName().isEmpty()) {
            error = "Name cannot be empty";
        }
        else if (foodCombo.getDiscountDetails().getDescription() == null || foodCombo.getDiscountDetails().getDescription().isEmpty()) {
            error = "Description cannot be empty";
        }
        else if (foodCombo.getDiscountDetails().getDiscountType() == null || foodCombo.getDiscountDetails().getDiscountType().isEmpty()) {
            error = "Discount type cannot be empty";
        }
        else if (!(foodCombo.getDiscountDetails().getDiscountType().equals("Price Offer")) && !(foodCombo.getDiscountDetails().getDiscountType().equals("Promotional Offer"))) {
            error = "Discount type not valid";
        }
        else if (foodCombo.getDiscountDetails().getDiscountAmount() < 0 ) {
            error = "Discount amount not valid";
        }
        else if (foodCombo.getDiscountDetails().getStartDate() == null || foodCombo.getDiscountDetails().getStartDate().toString().isEmpty()) {
            error = "Start date not valid";
        }
        else if (LocalDateTime.parse(foodCombo.getDiscountDetails().getStartDate().toString()).isBefore(now)) {
            error = "Start date not valid";
        }
        else if (foodCombo.getDiscountDetails().getEndDate() == null || foodCombo.getDiscountDetails().getEndDate().toString().isEmpty()) {
            error = "End date not valid";
        }
        else if (LocalDateTime.parse(foodCombo.getDiscountDetails().getEndDate().toString()).isBefore(LocalDateTime.parse(foodCombo.getDiscountDetails().getStartDate().toString()))) {
            error = "End date not valid";
        }
        else if (foodCombo.getDiscountDetails().getCafeId() == null || foodCombo.getDiscountDetails().getCafeId().isEmpty()) {
            error = "Cafe Id cannot be empty";
        }
        else if (foodCombo.getDiscountDetails().getIsActive() < 0 || foodCombo.getDiscountDetails().getIsActive() > 1) {
            error = "Discount availability value not valid";
        }
        else if (foodCombo.getDiscountDetails().getMenuItemId() == null || foodCombo.getDiscountDetails().getMenuItemId().isEmpty()) {
            error = "MenuItem Id cannot be empty";
        }
        else if (foodCombo.getDiscountDetails().getMenuItemType() == null || foodCombo.getDiscountDetails().getMenuItemType().isEmpty()) {
            error = "MenuItem type cannot be empty";
        }
        else if (!(foodCombo.getDiscountDetails().getMenuItemType().equals("Food Combo"))) {
            error = "MenuItem type not valid";
        }
        else if (foodCombo.getDiscountDetails().getOfferDetails() == null || foodCombo.getDiscountDetails().getOfferDetails().isEmpty()) {
            error = "Offer details cannot be empty";
        }

        if (error == null) {
            foodCombo.setDeleted(0);
            foodCombo.setDiscountStatus(0);
            foodComboRepository.save(foodCombo);
        }
        return error;
    }

    // Retrieve all food combos
    public List<FoodCombo> getFoodCombos() {
        return foodComboRepository.findAll();
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
        if (foodCombo.getAvailable() != 0 && foodCombo.getAvailable() != 1) {
            existingFoodCombo.setAvailable(foodCombo.getAvailable());
        }
        if (foodCombo.getDeleted() != 0 && foodCombo.getDeleted() != 1) {
            existingFoodCombo.setDeleted(foodCombo.getDeleted());
        }
        if (foodCombo.getDiscountStatus() == 0 || foodCombo.getDiscountStatus() == 1) {
            existingFoodCombo.setDiscountStatus(foodCombo.getDiscountStatus());
        }
        if (foodCombo.getRating() != null) {
            existingFoodCombo.setRating(foodCombo.getRating());
        }
        if (foodCombo.getDiscountDetails().getName() != null && !foodCombo.getDiscountDetails().getName().isEmpty()) {
            existingFoodCombo.getDiscountDetails().setName(foodCombo.getDiscountDetails().getName());
        }
        if (foodCombo.getDiscountDetails().getDescription() != null && !foodCombo.getDiscountDetails().getDescription().isEmpty()) {
            existingFoodCombo.getDiscountDetails().setDescription(foodCombo.getDiscountDetails().getDescription());
        }
        if (foodCombo.getDiscountDetails().getDiscountType().equals("Price Offer") || foodCombo.getDiscountDetails().getDiscountType().equals("Promotional Offer")) {
            existingFoodCombo.getDiscountDetails().setDiscountType(foodCombo.getDiscountDetails().getDiscountType());
        }
        if (foodCombo.getDiscountDetails().getDiscountAmount() > 0) {
            existingFoodCombo.getDiscountDetails().setDiscountAmount(foodCombo.getDiscountDetails().getDiscountAmount());
        }
        if (foodCombo.getDiscountDetails().getStartDate() != null && !foodCombo.getDiscountDetails().getStartDate().toString().isEmpty() && !LocalDateTime.parse(foodCombo.getDiscountDetails().getStartDate().toString()).isBefore(now)) {
            existingFoodCombo.getDiscountDetails().setStartDate(foodCombo.getDiscountDetails().getStartDate());
        }
        if (foodCombo.getDiscountDetails().getEndDate() != null && !foodCombo.getDiscountDetails().getEndDate().toString().isEmpty() && !LocalDateTime.parse(foodCombo.getDiscountDetails().getEndDate().toString()).isBefore(LocalDateTime.parse(foodCombo.getDiscountDetails().getStartDate().toString()))) {
            existingFoodCombo.getDiscountDetails().setEndDate(foodCombo.getDiscountDetails().getEndDate());
        }
        if (foodCombo.getDiscountDetails().getCafeId() != null && !foodCombo.getDiscountDetails().getCafeId().isEmpty()) {
            existingFoodCombo.getDiscountDetails().setCafeId(foodCombo.getDiscountDetails().getCafeId());
        }
        if (foodCombo.getDiscountDetails().getIsActive() == 0 || foodCombo.getDiscountDetails().getIsActive() == 1) {
            existingFoodCombo.getDiscountDetails().setIsActive(foodCombo.getDiscountDetails().getIsActive());
        }
        if (foodCombo.getDiscountDetails().getMenuItemId() != null && !foodCombo.getDiscountDetails().getMenuItemId().isEmpty()) {
            existingFoodCombo.getDiscountDetails().setMenuItemId(existingFoodCombo.getDiscountDetails().getMenuItemId());
        }
        if (foodCombo.getDiscountDetails().getMenuItemType().equals("Food Combo")) {
            existingFoodCombo.getDiscountDetails().setMenuItemType(existingFoodCombo.getDiscountDetails().getMenuItemType());
        }
        if (foodCombo.getDiscountDetails().getOfferDetails() != null && !foodCombo.getDiscountDetails().getOfferDetails().isEmpty()) {
            existingFoodCombo.getDiscountDetails().setOfferDetails(foodCombo.getDiscountDetails().getOfferDetails());
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
}
