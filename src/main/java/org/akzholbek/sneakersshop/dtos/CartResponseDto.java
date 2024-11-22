package org.akzholbek.sneakersshop.dtos;

import java.util.List;

public record CartResponseDto(
        Long cartId,

        List<CartItemResponseDto> items,
        Double totalAmount,
        Double taxAmount

) {
}
