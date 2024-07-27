package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.Cart;
import org.lafresca.lafrescabackend.Models.CustomFeature;
import org.lafresca.lafrescabackend.Models.FoodCombo;
import org.lafresca.lafrescabackend.Models.FoodItem;
import org.lafresca.lafrescabackend.Repositories.CartRepository;
import org.lafresca.lafrescabackend.Repositories.FoodComboRepository;
import org.lafresca.lafrescabackend.Repositories.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

        if (error == null) {
            cartRepository.save(cart);
        }

        return error;
    }

    // Get all cart items by UserId
    public List<Cart> getCartItems(String userId) {
        return cartRepository.findByUserId(userId);
    }

    // Delete cart item by id
    public void deleteCartItem(String id) {
        cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart Item Not Found with Id: " + id));
        cartRepository.deleteById(id);
    }

    // Update cart item ny id
    public void updateCartItem(String id, Cart cart) {
        Cart existingCart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart Item Not Found with Id: " + id));

        if (cart.getItemTotalPrice() > 0) {
            existingCart.setItemTotalPrice(cart.getItemTotalPrice());
        }

        cartRepository.save(existingCart);
    }
}
