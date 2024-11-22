package org.akzholbek.sneakersshop.mapper;

import org.akzholbek.sneakersshop.dtos.CartItemResponseDto;
import org.akzholbek.sneakersshop.dtos.CartResponseDto;
import org.akzholbek.sneakersshop.entity.Cart;
import org.akzholbek.sneakersshop.entity.CartItem;


import java.util.List;

public class CartMapper {

    public static CartItemResponseDto toCartItemResponseDto(CartItem cartItem) {
        return new CartItemResponseDto(
                cartItem.getProduct().getId(),
                cartItem.getProduct().getImages().isEmpty() ? null : cartItem.getProduct().getImages().get(0).getImageUrl(), // First image
                cartItem.getProduct().getName(),
                cartItem.getProduct().getDescription()
        );
    }

    public static CartResponseDto toCartResponseDto(Cart cart) {

        List<CartItemResponseDto> items = cart.getCartItems().stream()
                .map(CartMapper::toCartItemResponseDto)
                .toList();


        return new CartResponseDto(
                cart.getId(),
                items,
                cart.getTotalAmount(),
                cart.getTotalAmount()
        );
    }
}
