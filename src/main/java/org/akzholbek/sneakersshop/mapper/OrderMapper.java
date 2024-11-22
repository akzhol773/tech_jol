package org.akzholbek.sneakersshop.mapper;

import org.akzholbek.sneakersshop.dtos.OrderItemResponseDto;
import org.akzholbek.sneakersshop.dtos.OrderResponseDto;
import org.akzholbek.sneakersshop.entity.Order;
import org.akzholbek.sneakersshop.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderResponseDto toOrderResponseDto(Order order, List<OrderItemResponseDto> orderItemDtos, Double totalAmount, String deliveryAddress) {
        return new OrderResponseDto(
                order.getId(),
                order.getCreatedAt(),
                deliveryAddress,
                orderItemDtos,
                totalAmount
        );
    }


    public OrderItemResponseDto toOrderItemResponseDto(OrderItem orderItem) {
        return new OrderItemResponseDto(
                orderItem.getProduct().getId(),
                orderItem.getProduct().getName(),
                orderItem.getQuantity(),
                orderItem.getProduct().getPrice()
        );
    }

    public List<OrderItemResponseDto> toOrderItemResponseDtoList(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(this::toOrderItemResponseDto)
                .collect(Collectors.toList());
    }
}