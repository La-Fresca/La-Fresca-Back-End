package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.*;
import org.lafresca.lafrescabackend.Repositories.CartRepository;
import org.lafresca.lafrescabackend.Repositories.FoodComboRepository;
import org.lafresca.lafrescabackend.Repositories.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static io.jsonwebtoken.lang.Collections.size;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final FoodComboRepository foodComboRepository;
    private final FoodItemRepository foodItemRepository;

    @Autowired
    public CartService(CartRepository cartRepository, FoodComboRepository foodComboRepository, FoodItemRepository foodItemRepository) { this.cartRepository = cartRepository;
        this.foodComboRepository = foodComboRepository;
        this.foodItemRepository = foodItemRepository;
    }

    // Add New Item
    public String addNewItemToCart(Cart cart) {
        String error = null;

        if (cart.getUserId() == null || cart.getUserId().isEmpty()) {
            error = "User id is required";
        }
        else if (cart.getMenuItemId() == null) {
            error = "MenuItem id cannot be null";
        }
        else if (cart.getQuantity() <= 0) {
            error = "Quantity must be greater than 0";
        }
        else if (cart.getMenuItemType() == null) {
            error = "MenuItem type cannot be null";
        }

        if (Objects.equals(cart.getMenuItemType(), "Food Item")) {
            FoodItem foodItem = foodItemRepository.findById(cart.getMenuItemId()).orElse(null);

            if (foodItem != null) {
                if (foodItem.getDeleted() == 1) {
                    error = "Food Item not found";
                }
                else {
                    List<CartItemFeature> additionalFeatures = cart.getCustomFeatures();
                    FoodItem foodItemDetails = foodItemRepository.findById(cart.getMenuItemId()).orElse(null);
                    List<CustomFeature> FeatureList = foodItemDetails.getFeatures();

                    int count = 0;
                    double totalAdditionalPrice = 0;

                    for (CustomFeature feature : FeatureList){
                        List<Double> priceList = feature.getAdditionalPrices();
                        if (additionalFeatures.get(count).getLevel() != -1) {
                            totalAdditionalPrice += priceList.get(count);
                        }
                        count ++;
                    }

                    double totalPrice = (foodItem.getPrice() + totalAdditionalPrice) * cart.getQuantity();
                    cart.setItemTotalPrice(totalPrice);
                }
            }
        }

        else if (Objects.equals(cart.getMenuItemType(), "Food Combo")) {
            FoodCombo foodCombo = foodComboRepository.findById(cart.getMenuItemId()).orElse(null);

            if (foodCombo != null) {
                if (foodCombo.getDeleted() == 1) {
                    error = "Food Combo not found";
                }
                else {
                    double totalPrice = foodCombo.getPrice() * cart.getQuantity();
                    cart.setItemTotalPrice(totalPrice);
                }
            }
        }

        else {
            error =  "Invalid MenuItem type";
        }

        if (error == null) {
            cartRepository.save(cart);
        }

        return error;
    }

    // Get all cart items by UserId
    public List<Cart> getCartItems(String userId) {
        List<Cart> cartList = cartRepository.findByUserId(userId);

        if (!cartList.isEmpty()) {
            for (Cart cart : cartList) {
                String menuItemType = cart.getMenuItemType();
                String menuItemId = cart.getMenuItemId();
                if (Objects.equals(menuItemType, "Food Item")) {
                    FoodItem foodItem = foodItemRepository.findById(menuItemId).orElse(null);
                    assert foodItem != null;
                    if (foodItem.getDiscountStatus() == 1) {
                        if (Objects.equals(foodItem.getDiscountDetails().getDiscountType(), "Price Offer")) {
                            double price = cart.getItemTotalPrice();
                            double discountAmount = foodItem.getDiscountDetails().getDiscountAmount();
                            double discountedPrice = price - (price * discountAmount) / 100;
                            cart.setDiscountedPrice(discountedPrice);
                        }
                    }
                }

                else if (Objects.equals(menuItemType, "Food Combo")) {
                    FoodCombo foodCombo = foodComboRepository.findById(menuItemId).orElse(null);
                    assert foodCombo != null;
                    if (foodCombo.getDiscountStatus() == 1) {
                        if (Objects.equals(foodCombo.getDiscountDetails().getDiscountType(), "Price Offer")) {
                            double price = cart.getItemTotalPrice();
                            double discountAmount = foodCombo.getDiscountDetails().getDiscountAmount();
                            double discountedPrice = price - (price * discountAmount) / 100;
                            cart.setDiscountedPrice(discountedPrice);
                        }
                    }
                }
            }
        }

        return cartList;
    }

    // Delete cart item by id
    public void deleteCartItem(String id) {
        cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart Item Not Found with Id: " + id));
        cartRepository.deleteById(id);
    }

    // Update cart item by id
    public void updateCartItem(String id, Cart cart) {
        Cart existingCart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart Item Not Found with Id: " + id));

        if (cart.getItemTotalPrice() > 0) {
            existingCart.setItemTotalPrice(cart.getItemTotalPrice());
        }

        cartRepository.save(existingCart);
    }
}
