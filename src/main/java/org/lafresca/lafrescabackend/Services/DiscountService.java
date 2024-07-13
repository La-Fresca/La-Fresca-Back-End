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

    // Search discount
    public Optional<Discount> getDiscount(String id) {
        discountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Discount not found with id "+ id));
        return discountRepository.findById(id);}
}
