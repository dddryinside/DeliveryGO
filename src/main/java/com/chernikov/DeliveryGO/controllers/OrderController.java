package com.chernikov.DeliveryGO.controllers;

import com.chernikov.DeliveryGO.requests.OrderRequest;
import com.chernikov.DeliveryGO.service.OrderService;
import com.chernikov.DeliveryGO.service.UserService;
import com.chernikov.DeliveryGO.utils.Converter;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/api/create-order")
    public void createOrder(@RequestBody OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
    }

    @DeleteMapping("/api/delete-order/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }

    @GetMapping("/api/get-client-orders")
    public List<OrderRequest> getAllClientOrders() {
        return userService.getUserFromContext().getOrders()
                .stream()
                .map(Converter::convertOrderRequest)
                .toList();
    }
}