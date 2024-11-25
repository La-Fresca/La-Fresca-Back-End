package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.Discount;
import org.lafresca.lafrescabackend.Models.FoodCombo;
import org.lafresca.lafrescabackend.Models.FoodItem;
import org.lafresca.lafrescabackend.Repositories.FoodComboRepository;
import org.lafresca.lafrescabackend.Repositories.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountService {
    private final FoodItemRepository foodItemRepository;
    private final FoodComboRepository foodComboRepository;

    @Autowired
    public DiscountService(FoodItemRepository foodItemRepository, FoodComboRepository foodComboRepository) {
        this.foodItemRepository = foodItemRepository;
        this.foodComboRepository = foodComboRepository;
    }

    // Add new discount
    public String addNewDiscount(Discount discount) {
        String error = null;
        LocalDateTime now = LocalDateTime.now();

        if (discount.getName() == null || discount.getName().isEmpty()) {
            error = "Name cannot be empty";
        }
        else if (discount.getDescription() == null || discount.getDescription().isEmpty()) {
            error = "Description cannot be empty";
        }
        else if (discount.getDiscountType() == null || discount.getDiscountType().isEmpty()) {
            error = "Discount type cannot be empty";
        }
        else if (!(discount.getDiscountType().equals("Price Offer")) && !(discount.getDiscountType().equals("Promotional Offer"))) {
            error = "Discount type not valid";
        }
        else if (discount.getDiscountType().equals("Promotional Offer")) {
            if (discount.getAmount() == null) {
                error = "Amount cannot be empty";
            }
            else if (discount.getAmount() < 0) {
                error = "Amount not valid";
            }
        }
//        else if (discount.getDiscountType().equals("Price Offer")) {
//            if (discount.getDiscountAmount() == null) {
//                error = "Discount Amount cannot be empty";
//            }
//
//            else {
//                FoodItem foodItem = foodItemRepository.findOneById(discount.getMenuItemId());
//
//                if (discount.getDiscountAmount() < foodItem.getCost()) {
//
//                }
//            }
//
//        }
        else if (discount.getDiscountAmount() < 0 ) {
            error = "Discount amount not valid";
        }
        else if (discount.getStartDate() == null || discount.getStartDate().toString().isEmpty()) {
            error = "Start date not valid";
        }
        else if (LocalDateTime.parse(discount.getStartDate().toString()).isBefore(now)) {
            error = "Start date not valid";
        }
        else if (discount.getEndDate() == null || discount.getEndDate().toString().isEmpty()) {
            error = "End date not valid";
        }
        else if (LocalDateTime.parse(discount.getEndDate().toString()).isBefore(LocalDateTime.parse(discount.getStartDate().toString()))) {
            error = "End date not valid";
        }
        else if (discount.getMenuItemId() == null || discount.getMenuItemId().isEmpty()) {
            error = "MenuItem Id cannot be empty";
        }
        else if (discount.getMenuItemType() == null || discount.getMenuItemType().isEmpty()) {
            error = "MenuItem type cannot be empty";
        }
        else if (!(discount.getMenuItemType().equals("Food Item")) && !(discount.getMenuItemType().equals("Food Combo"))) {
            error = "MenuItem type not valid";
        }
        else if (discount.getOfferDetails() == null || discount.getOfferDetails().isEmpty()) {
            error = "Offer details cannot be empty";
        }

        if (error == null) {
            if (discount.getDiscountType().equals("Price Offer")) {
                discount.setAmount(0);
            }

            if (discount.getMenuItemType().equals("Food Item")) {
                FoodItem foodItem = foodItemRepository.findOneById(discount.getMenuItemId());

                if (foodItem.getDiscountStatus() == 1) {
                    return "Active discount already in progress";
                }

                foodItem.setDiscountStatus(1);
                foodItem.setDiscountDetails(discount);

                foodItemRepository.save(foodItem);
            }

            else if (discount.getMenuItemType().equals("Food Combo")) {
                FoodCombo foodCombo = foodComboRepository.findOneById(discount.getMenuItemId());

                if (foodCombo.getDiscountStatus() == 1) {
                    return "Active discount already in progress";
                }

                foodCombo.setDiscountStatus(1);
                foodCombo.setDiscountDetails(discount);

                foodComboRepository.save(foodCombo);
            }
        }

        return error;
    }

    // Retrieve all discounts
    public List<List<Discount>> getDiscounts() {
        List<FoodItem> foodItemList = foodItemRepository.findAllByDiscountStatus();
        List<FoodCombo> foodComboList = foodComboRepository.findAllByDiscountStatus();

        List<Discount> foodItemDiscount = new ArrayList<>();
        List<Discount> foodComboDiscount = new ArrayList<>();

        for (FoodItem foodItem : foodItemList) {
            foodItemDiscount.add(foodItem.getDiscountDetails());
        }

        for (FoodCombo foodCombo : foodComboList) {
            foodComboDiscount.add(foodCombo.getDiscountDetails());
        }

        List<List<Discount>> discounts = new ArrayList<>();
        discounts.add(foodItemDiscount);
        discounts.add(foodComboDiscount);

        return discounts;
    }


    // Retrieve all discounts by CafeId
    public List<List<Discount>> getDiscounts(String cafeId) {
        List<FoodItem> foodItemList = foodItemRepository.findDiscountByStatus(cafeId);
        List<FoodCombo> foodComboList = foodComboRepository.findDiscountByStatus(cafeId);

        List<Discount> foodItemDiscount = new ArrayList<>();
        List<Discount> foodComboDiscount = new ArrayList<>();

        for (FoodItem foodItem : foodItemList) {
            foodItemDiscount.add(foodItem.getDiscountDetails());
        }

        for (FoodCombo foodCombo : foodComboList) {
            foodComboDiscount.add(foodCombo.getDiscountDetails());
        }

        List<List<Discount>> discounts = new ArrayList<>();
        discounts.add(foodItemDiscount);
        discounts.add(foodComboDiscount);

        return discounts;
    }


    // Search discount by id
    public Discount getDiscount(String menuItemId) {
        FoodItem foodItem = foodItemRepository.findOneById(menuItemId);
        FoodCombo foodCombo = foodComboRepository.findOneById(menuItemId);

        if (foodItem != null) {
            return foodItem.getDiscountDetails();
        }
        else if (foodCombo != null) {
            return foodCombo.getDiscountDetails();
        }
        else {
            return null;
        }
    }


    // Delete discount by id
    public String deleteDiscount(String menuItemId) {
        FoodItem foodItem = foodItemRepository.findOneById(menuItemId);
        FoodCombo foodCombo = foodComboRepository.findOneById(menuItemId);

        if (foodItem != null) {
            foodItem.setDiscountStatus(0);
            foodItemRepository.save(foodItem);
            return "Discount successfully deleted";
        }
        else if (foodCombo != null) {
            foodCombo.setDiscountStatus(0);
            foodComboRepository.save(foodCombo);
            return "Discount successfully deleted";
        }
        else {
            return "Discount not found";
        }
    }


    // Update Discount by id
    public void updateDiscount(String menuItemId, Discount discount) {
        LocalDateTime now = LocalDateTime.now();

        if (discount.getMenuItemType().equals("Food Item")) {
            FoodItem existingFoodItem = foodItemRepository.findOneById(menuItemId);

            if (discount.getName() != null && !discount.getName().isEmpty()) {
                existingFoodItem.getDiscountDetails().setName(discount.getName());
            }
            if (discount.getDescription() != null && !discount.getDescription().isEmpty()) {
                existingFoodItem.getDiscountDetails().setDescription(discount.getDescription());
            }
            if (discount.getDiscountType().equals("Price Offer")) {
                existingFoodItem.getDiscountDetails().setDiscountType("Price Offer");
            }
            if (discount.getDiscountAmount() > 0) {
                existingFoodItem.getDiscountDetails().setDiscountAmount(discount.getDiscountAmount());
            }
            if (discount.getStartDate() != null && !discount.getStartDate().toString().isEmpty() && !LocalDateTime.parse(discount.getStartDate().toString()).isBefore(now)) {
                existingFoodItem.getDiscountDetails().setStartDate(discount.getStartDate());
            }
            if (discount.getEndDate() != null && !discount.getEndDate().toString().isEmpty() && !LocalDateTime.parse(discount.getEndDate().toString()).isBefore(LocalDateTime.parse(discount.getStartDate().toString()))) {
                existingFoodItem.getDiscountDetails().setEndDate(discount.getEndDate());
            }
            if (discount.getOfferDetails() != null && !discount.getOfferDetails().isEmpty()) {
                existingFoodItem.getDiscountDetails().setOfferDetails(discount.getOfferDetails());
            }

            foodItemRepository.save(existingFoodItem);
        }
        else if (discount.getMenuItemType().equals("Food Combo")) {
            FoodCombo existingFoodCombo = foodComboRepository.findOneById(menuItemId);

            if (discount.getName() != null && !discount.getName().isEmpty()) {
                existingFoodCombo.getDiscountDetails().setName(discount.getName());
            }
            if (discount.getDescription() != null && !discount.getDescription().isEmpty()) {
                existingFoodCombo.getDiscountDetails().setDescription(discount.getDescription());
            }
            if (discount.getDiscountType().equals("Price Offer")) {
                existingFoodCombo.getDiscountDetails().setDiscountType("Price Offer");
            }
            if (discount.getDiscountAmount() > 0) {
                existingFoodCombo.getDiscountDetails().setDiscountAmount(discount.getDiscountAmount());
            }
            if (discount.getStartDate() != null && !discount.getStartDate().toString().isEmpty() && !LocalDateTime.parse(discount.getStartDate().toString()).isBefore(now)) {
                existingFoodCombo.getDiscountDetails().setStartDate(discount.getStartDate());
            }
            if (discount.getEndDate() != null && !discount.getEndDate().toString().isEmpty() && !LocalDateTime.parse(discount.getEndDate().toString()).isBefore(LocalDateTime.parse(discount.getStartDate().toString()))) {
                existingFoodCombo.getDiscountDetails().setEndDate(discount.getEndDate());
            }
            if (discount.getOfferDetails() != null && !discount.getOfferDetails().isEmpty()) {
                existingFoodCombo.getDiscountDetails().setOfferDetails(discount.getOfferDetails());
            }

            foodComboRepository.save(existingFoodCombo);
        }
    }
}