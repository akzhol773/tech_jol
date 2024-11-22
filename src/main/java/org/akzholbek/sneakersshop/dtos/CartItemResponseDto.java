package org.akzholbek.sneakersshop.dtos;

public record CartItemResponseDto (
        Long productId,
        String imageUrl,
        String name,
        String description
){
}
