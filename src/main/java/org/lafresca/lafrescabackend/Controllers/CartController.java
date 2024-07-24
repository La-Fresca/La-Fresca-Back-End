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

    // Get all cart items
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

    // Delete cart item by id
    @DeleteMapping(path = "{id}")
    @Operation(
            description = "Delete cart items by id",
            summary = "Delete menu items from cart by using id",
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

    public void deleteCartItem(@PathVariable("id") String id) { cartService.deleteCartItem(id); }

    // Update cart item by id
    @PutMapping(path = "{id}")
    @Operation(
            description = "Update cart items by id",
            summary = "Update menu items from cart by using id",
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

    public void updateCartItem(@PathVariable("id") String id, @RequestBody Cart cart) { cartService.updateCartItem(id, cart); }
}
