package org.akzholbek.sneakersshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.akzholbek.sneakersshop.dtos.CartRequestDto;
import org.akzholbek.sneakersshop.dtos.CartResponseDto;
import org.akzholbek.sneakersshop.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartController {

    CartService cartService;



    @Operation(
            summary = "ADD A PRODUCT TO THE CART",
            description = "Adds a product to the cart and takes user ID, product ID as body",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Item added to the cart"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @PostMapping("/add-to-cart")
    public ResponseEntity<String> addToCart(@RequestBody CartRequestDto cartRequest) {
        return ResponseEntity.ok(cartService.addToCart(cartRequest));
    }

    @Operation(
            summary = "GET ALL ITEMS FROM THE CART",
            description = "Get all items from the cart",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Items list returned"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping("/get-cart-items")
    public List<CartResponseDto> getCartItems(@RequestBody Long userId) {
        return cartService.getCartItems(userId);
    }


    @Operation(
            summary = "DELETE ITEM FROM THE CART",
            description = "Delete item from the cart",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Items list returned"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @DeleteMapping("/delete-cart-item")
    public ResponseEntity<String> removeItemFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        return ResponseEntity.ok(cartService.removeItemFromCart(userId, productId));
    }



}