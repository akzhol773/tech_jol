package org.akzholbek.sneakersshop.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDto(
        Long orderId,
        LocalDateTime createdAt,
        String deliveryAddress,
        List<OrderItemResponseDto> items,
        Double totalAmount
) {}