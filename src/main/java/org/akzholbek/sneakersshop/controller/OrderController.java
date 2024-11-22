package org.akzholbek.sneakersshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.akzholbek.sneakersshop.dtos.OrderRequestDto;
import org.akzholbek.sneakersshop.dtos.OrderResponseDto;
import org.akzholbek.sneakersshop.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/orders")
public class OrderController {

    OrderService orderService;

    @Operation(
            summary = "ORDER PRODUCTS",
            description = "Order products based on the cart of the user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Returns order response DTO"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @PostMapping("/order")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequest) {
        return orderService.createOrder(orderRequest);
    }
}
