package org.akzholbek.sneakersshop.dtos;

public record OrderItemResponseDto(
        Long productId,
        String productName,
        Integer quantity,
        Double price
) {}
