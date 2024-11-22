package org.akzholbek.sneakersshop.service;

import org.akzholbek.sneakersshop.dtos.CartRequestDto;
import org.akzholbek.sneakersshop.dtos.CartResponseDto;
import org.akzholbek.sneakersshop.entity.Cart;

import java.util.List;

public interface CartService {
    String addToCart(CartRequestDto cartRequest);

    List<CartResponseDto> getCartItems(Long userId);

    String removeItemFromCart(Long userId, Long productId);

}
