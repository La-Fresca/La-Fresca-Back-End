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
        LocalDateTime now = LocalDateTime.now();

        if (discount.getName() != null && !discount.getName().isEmpty()) {
            existingDiscount.setName(discount.getName());
        }
        if (discount.getDescription() != null && !discount.getDescription().isEmpty()) {
            existingDiscount.setDescription(discount.getDescription());
        }
        if (discount.getDiscountType().equals("Price Offer") || discount.getDiscountType().equals("Promotional Offer")) {
            existingDiscount.setDiscountType(discount.getDiscountType());
        }
        if (discount.getDiscountAmount() > 0) {
            existingDiscount.setDiscountAmount(discount.getDiscountAmount());
        }
        if (discount.getStartDate() != null && !discount.getStartDate().toString().isEmpty() && !LocalDateTime.parse(discount.getStartDate().toString()).isBefore(now)) {
            existingDiscount.setStartDate(discount.getStartDate());
        }
        if (discount.getEndDate() != null && !discount.getEndDate().toString().isEmpty() && !LocalDateTime.parse(discount.getEndDate().toString()).isBefore(LocalDateTime.parse(discount.getStartDate().toString()))) {
            existingDiscount.setEndDate(discount.getEndDate());
        }
        if (discount.getCafeId() != null && !discount.getCafeId().isEmpty()) {
            existingDiscount.setCafeId(discount.getCafeId());
        }
        if (discount.getIsActive() == 0 || discount.getIsActive() == 1) {
            existingDiscount.setIsActive(discount.getIsActive());
        }
        if (discount.getMenuItemId() != null && !discount.getMenuItemId().isEmpty()) {
            existingDiscount.setMenuItemId(existingDiscount.getMenuItemId());
        }
        if (discount.getMenuItemType().equals("Food Item") || discount.getMenuItemType().equals("Food Combo")) {
            existingDiscount.setMenuItemType(existingDiscount.getMenuItemType());
        }
        if (discount.getOfferDetails() != null && !discount.getOfferDetails().isEmpty()) {
            existingDiscount.setOfferDetails(discount.getOfferDetails());
        }

        discountRepository.save(existingDiscount);
    }
}