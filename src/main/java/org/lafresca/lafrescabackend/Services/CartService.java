package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Models.Cart;
import org.lafresca.lafrescabackend.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) { this.cartRepository = cartRepository; }


    public String addNewItemToCart(Cart cart) {
        String error = null;

        if (cart.getUserId() == null || cart.getUserId().isEmpty()) {
            error = "User id is required";
        }
        else if (cart.getTotalPrice() < 0) {
            error = "Invalid total price";
        }

        if (error == null) {
            cartRepository.save(cart);
        }

        return error;
    }
}
