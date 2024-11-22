package org.akzholbek.sneakersshop.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;

public record CartRequestDto(
        Long userId,
        Long productId,

        @Min(value = 1, message = "Quantity should be at least one")
        Integer quantity
) {
}
