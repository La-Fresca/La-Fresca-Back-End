package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.lafresca.lafrescabackend.Models.Cart;
import org.lafresca.lafrescabackend.Services.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/lafresca/cart")
@AllArgsConstructor
@Tag(name = "Cart Controller")
public class CartController {
    private final CartService cartService;

    // Add new item
    @PostMapping
    @Operation(
            description = "Add to cart",
            summary = "Add new menu item to cart",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })

    public String addNewItemToCart(@RequestBody Cart cart) { return cartService.addNewItemToCart(cart); }

    // Get all items
    @GetMapping(path = "{userid}")
    @Operation(
            description = "Get all items",
            summary = "Retrieve all menu items from cart",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })

    public List<Cart> getCartItems(@PathVariable("userid") String userId) { return cartService.getCartItems(userId); }

}
