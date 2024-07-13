package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.Discount;
import org.lafresca.lafrescabackend.Repositories.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {
    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountService(DiscountRepository discountRepository) { this.discountRepository = discountRepository; }

    // Add new discount
    public String addDiscount(Discount discount) {
        String error = null;

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
//        else if (!LocalDateTime.toInstant().isBefore(discount.getStartDate().toInstant())) {
//            error = "Start date not valid";
//        }
//        else if (!discount.getEndDate().toInstant().isAfter(discount.getStartDate().toInstant())) {
//            error = "End date not valid";
//        }
        else if (discount.getCafeId() == null || discount.getCafeId().isEmpty()) {
            error = "Cafe Id cannot be empty";
        }
        else if (discount.getIsActive() < 0 || discount.getIsActive() > 1) {
            error = "Discount availability value not valid";
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
            discountRepository.save(discount);
        }
        return error;
    }

    // Retrieve all discounts
    public List<Discount> getDiscounts() { return discountRepository.findAll(); }

    // Search discount by id
    public Optional<Discount> getDiscount(String id) {
        discountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Discount not found with id " + id));
        return discountRepository.findById(id);
    }

    // Delete discount by id
    public void deleteDiscount(String id) {
        discountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Discount not found with id " + id));
        discountRepository.deleteById(id);
    }

    // Update Discount by id
    public void updateDiscount(String id, Discount discount) {
        Discount existingDiscount = discountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Discount not found with id " + id));

        if (existingDiscount.getName() != null && !existingDiscount.getName().isEmpty()) {
            existingDiscount.setName(existingDiscount.getName());
        }
        if (existingDiscount.getDescription() != null && !existingDiscount.getDescription().isEmpty()) {
            existingDiscount.setDescription(existingDiscount.getDescription());
        }
        if (existingDiscount.getDiscountType().equals("Price Offer") || existingDiscount.getDiscountType().equals("Promotional Offer")) {
            existingDiscount.setDiscountType(existingDiscount.getDiscountType());
        }
        if (existingDiscount.getDiscountAmount() > 0) {
            existingDiscount.setDiscountAmount(existingDiscount.getDiscountAmount());
        }
        if (existingDiscount.getCafeId() != null && !existingDiscount.getCafeId().isEmpty()) {
            existingDiscount.setCafeId(existingDiscount.getCafeId());
        }
        if (existingDiscount.getIsActive() == 0 || existingDiscount.getIsActive() == 1) {
            existingDiscount.setIsActive(existingDiscount.getIsActive());
        }
        if (existingDiscount.getMenuItemId() != null && !existingDiscount.getMenuItemId().isEmpty()) {
            existingDiscount.setMenuItemId(existingDiscount.getMenuItemId());
        }
        if (existingDiscount.getMenuItemType().equals("Food Item") || existingDiscount.getMenuItemType().equals("Food Combo")) {
            existingDiscount.setMenuItemType(existingDiscount.getMenuItemType());
        }
        if (existingDiscount.getOfferDetails() != null && !existingDiscount.getOfferDetails().isEmpty()) {
            existingDiscount.setOfferDetails(existingDiscount.getOfferDetails());
        }
//        else if (!LocalDateTime.toInstant().isBefore(discount.getStartDate().toInstant())) {
//            error = "Start date not valid";
//        }
//        else if (!discount.getEndDate().toInstant().isAfter(discount.getStartDate().toInstant())) {
//            error = "End date not valid";
//        }

        discountRepository.save(existingDiscount);
    }
}
