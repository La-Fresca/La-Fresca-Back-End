package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.Cart;
import org.lafresca.lafrescabackend.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) { this.cartRepository = cartRepository; }

    // Add New Item
    public String addNewItemToCart(Cart cart) {
        String error = null;

        if (cart.getUserId() == null || cart.getUserId().isEmpty()) {
            error = "User id is required";
        }
        else if (cart.getTotalPrice() == null) {
            error = "Total price cannot be null";
        }
        else if (cart.getTotalPrice() < 0) {
            error = "Invalid total price";
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
}
