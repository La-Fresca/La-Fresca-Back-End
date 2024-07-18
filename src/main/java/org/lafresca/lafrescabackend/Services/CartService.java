package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) { this.cartRepository = cartRepository; }


}
