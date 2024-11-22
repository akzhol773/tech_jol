package org.akzholbek.sneakersshop.service;

import org.akzholbek.sneakersshop.dtos.OrderRequestDto;
import org.akzholbek.sneakersshop.dtos.OrderResponseDto;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequest);
}
