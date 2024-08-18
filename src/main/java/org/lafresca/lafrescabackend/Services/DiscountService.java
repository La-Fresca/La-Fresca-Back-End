package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.Discount;
import org.lafresca.lafrescabackend.Models.FoodCombo;
import org.lafresca.lafrescabackend.Models.FoodItem;
import org.lafresca.lafrescabackend.Repositories.FoodComboRepository;
import org.lafresca.lafrescabackend.Repositories.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
            if (discount.getMenuItemType().equals("Food Item")) {
                FoodItem foodItem = foodItemRepository.findOneById(discount.getMenuItemId());
                foodItem.setDiscountStatus(1);
                foodItem.setDiscountDetails(discount);

                foodItemRepository.save(foodItem);
            }

            else if (discount.getMenuItemType().equals("Food Combo")) {
                FoodCombo foodCombo = foodComboRepository.findOneById(discount.getMenuItemId());
                foodCombo.setDiscountStatus(1);
                foodCombo.setDiscountDetails(discount);

                foodComboRepository.save(foodCombo);
            }
        }
        return error;
    }

    // Retrieve all discounts
    public List<Discount> getDiscounts() {
        List<Discount> foodItemList = foodItemRepository.findByStatus(1);
        List<Discount> foodComboList = foodComboRepository.findByStatus(1);

        foodComboList.addAll(foodItemList);

        return foodComboList;
    }

    // Search discount by id
//    public Optional<Discount> getDiscount(String id) {
//        discountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Discount not found with id " + id));
//        return discountRepository.findById(id);
//    }

    // Delete discount by id
//    public void deleteDiscount(String id) {
//        discountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Discount not found with id " + id));
//        discountRepository.deleteById(id);
//    }

    // Update Discount by id
//    public void updateDiscount(String id, Discount discount) {
//        if (discount.getMenuItemType().equals("Food Item")) {
//            FoodItem existingFoodItem = foodItemRepository.findOneById(discount.getMenuItemId());
//            LocalDateTime now = LocalDateTime.now();
//
//            if (discount.getName() != null && !discount.getName().isEmpty()) {
//                existingFoodItem.getDiscountDetails().setName(discount.getName());
//            }
//            if (discount.getDescription() != null && !discount.getDescription().isEmpty()) {
//                existingFoodItem.getDiscountDetails().setDescription(discount.getDescription());
//            }
//            if (discount.getDiscountType().equals("Price Offer")) {
//                existingFoodItem.getDiscountDetails().setDiscountType("Price Offer");
//            }
//            if (discount.getDiscountAmount() > 0) {
//                existingFoodItem.getDiscountDetails().setDiscountAmount(discount.getDiscountAmount());
//            }
//            if (discount.getStartDate() != null && !discount.getStartDate().toString().isEmpty() && !LocalDateTime.parse(discount.getStartDate().toString()).isBefore(now)) {
//                existingFoodItem.getDiscountDetails().setStartDate(discount.getStartDate());
//            }
//            if (discount.getEndDate() != null && !discount.getEndDate().toString().isEmpty() && !LocalDateTime.parse(discount.getEndDate().toString()).isBefore(LocalDateTime.parse(discount.getStartDate().toString()))) {
//                existingFoodItem.getDiscountDetails().setEndDate(discount.getEndDate());
//            }
//            if (discount.getOfferDetails() != null && !discount.getOfferDetails().isEmpty()) {
//                existingFoodItem.getDiscountDetails().setOfferDetails(discount.getOfferDetails());
//            }
//        }
//        else if (discount.getMenuItemType().equals("Food Combo")) {
//            FoodCombo existingFoodCombo = foodComboRepository.findOneById(discount.getMenuItemId());
//
//            if (discount.getName() != null && !discount.getName().isEmpty()) {
//                existingFoodCombo.getDiscountDetails().setName(discount.getName());
//            }
//            if (discount.getDescription() != null && !discount.getDescription().isEmpty()) {
//                existingFoodCombo.getDiscountDetails().setDescription(discount.getDescription());
//            }
//            if (discount.getDiscountType().equals("Price Offer")) {
//                existingFoodCombo.getDiscountDetails().setDiscountType("Price Offer");
//            }
//            if (discount.getDiscountAmount() > 0) {
//                existingFoodCombo.getDiscountDetails().setDiscountAmount(discount.getDiscountAmount());
//            }
//            if (discount.getStartDate() != null && !discount.getStartDate().toString().isEmpty() && !LocalDateTime.parse(discount.getStartDate().toString()).isBefore(now)) {
//                existingFoodCombo.getDiscountDetails().setStartDate(discount.getStartDate());
//            }
//            if (discount.getEndDate() != null && !discount.getEndDate().toString().isEmpty() && !LocalDateTime.parse(discount.getEndDate().toString()).isBefore(LocalDateTime.parse(discount.getStartDate().toString()))) {
//                existingFoodCombo.getDiscountDetails().setEndDate(discount.getEndDate());
//            }
//            if (discount.getOfferDetails() != null && !discount.getOfferDetails().isEmpty()) {
//                existingFoodCombo.getDiscountDetails().setOfferDetails(discount.getOfferDetails());
//            }
//        }
//    }
}