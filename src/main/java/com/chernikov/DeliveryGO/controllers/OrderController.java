package com.chernikov.DeliveryGO.controllers;

import com.chernikov.DeliveryGO.entities.Client;
import com.chernikov.DeliveryGO.enums.ROLE;
import com.chernikov.DeliveryGO.requests.OrderRequest;
import com.chernikov.DeliveryGO.service.OrderService;
import com.chernikov.DeliveryGO.service.UserService;
import com.chernikov.DeliveryGO.utils.Converter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        if (userService.getUserFromContext() instanceof Client client) {
            return client.getOrders()
                    .stream()
                    .map(Converter::convertOrderRequest)
                    .toList();
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/api/get-available-orders")
    public List<OrderRequest> getAvailableOrders() {
        if (userService.getUserFromContext().getRole().equals(ROLE.COURIER)) {
            return orderService.getAvailableOrders()
                    .stream().map(Converter::convertOrderRequest).toList();
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("api/get-order/{orderId}")
    public OrderRequest getOrder(@PathVariable Long orderId) {
        return Converter.convertOrderRequest(orderService.getOrder(orderId));
    }
}