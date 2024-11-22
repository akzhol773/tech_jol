package org.akzholbek.sneakersshop.dtos;

public record OrderRequestDto(
        Long userId,
        String deliveryAddress
) {}
