package org.akzholbek.sneakersshop.service.serviceImpl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.akzholbek.sneakersshop.dtos.OrderItemResponseDto;
import org.akzholbek.sneakersshop.dtos.OrderRequestDto;
import org.akzholbek.sneakersshop.dtos.OrderResponseDto;
import org.akzholbek.sneakersshop.entity.Cart;
import org.akzholbek.sneakersshop.entity.CartItem;
import org.akzholbek.sneakersshop.entity.Order;
import org.akzholbek.sneakersshop.entity.OrderItem;
import org.akzholbek.sneakersshop.exception.CartNotFoundException;
import org.akzholbek.sneakersshop.exception.ItemNotFoundException;
import org.akzholbek.sneakersshop.mapper.OrderMapper;
import org.akzholbek.sneakersshop.repository.CartRepository;
import org.akzholbek.sneakersshop.repository.OrderRepository;
import org.akzholbek.sneakersshop.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements OrderService {

    CartRepository cartRepository;
    OrderRepository orderRepository;
    OrderMapper orderMapper;

    @Transactional
    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequest) {

        Cart cart = cartRepository.findByUserId(orderRequest.userId())
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user ID: " + orderRequest.userId()));

        if (cart.getCartItems().isEmpty()) {
            throw new ItemNotFoundException("Cart is empty. Cannot place an order.");
        }


        Order order = new Order();
        order.setUser(cart.getUser());
        order.setCreatedAt(LocalDateTime.now());
        order.setOrderItems(new HashSet<>());


        double totalAmount = 0.0;
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);

            totalAmount += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }

        order.setTotalAmount(totalAmount);

        order = orderRepository.save(order);

        cart.getCartItems().clear();
        cartRepository.save(cart);

        List<OrderItemResponseDto> orderItemDtos = orderMapper.toOrderItemResponseDtoList(new ArrayList<>(order.getOrderItems()));

        return orderMapper.toOrderResponseDto(order, orderItemDtos, totalAmount, orderRequest.deliveryAddress());
    }
}
