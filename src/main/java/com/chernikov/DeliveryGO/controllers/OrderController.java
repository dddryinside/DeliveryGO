package com.chernikov.DeliveryGO.controllers;

import com.chernikov.DeliveryGO.entities.DeliveryOrder;
import com.chernikov.DeliveryGO.requests.OrderRequest;
import com.chernikov.DeliveryGO.service.OrderService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/api/create-order")
    public ResponseEntity<DeliveryOrder> createOrder(@RequestBody OrderRequest orderRequest) {
        return new ResponseEntity<>(orderService.createOrder(orderRequest), HttpStatus.OK);
    }

    @GetMapping("/api/get-orders")
    public ResponseEntity<List<OrderRequest>> getOrders(@RequestParam Long clientId) {
        return new ResponseEntity<>(orderService.getOrdersByClientId(clientId), HttpStatus.OK);
    }
}
