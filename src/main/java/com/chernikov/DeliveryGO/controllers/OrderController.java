package com.chernikov.DeliveryGO.controllers;

import com.chernikov.DeliveryGO.requests.OrderRequest;
import com.chernikov.DeliveryGO.requests.ReplyRequest;
import com.chernikov.DeliveryGO.requests.ReplyResponse;
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

    @PostMapping("/api/create-order")
    public void createOrder(@RequestBody OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
    }

    @DeleteMapping("/api/delete-order/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }

    @GetMapping("/api/get-orders")
    public List<OrderRequest> getOrders() {
        return orderService.getOrders().stream().map(Converter::convertOrderRequest).toList();
    }

    @GetMapping("api/get-order/{orderId}")
    public OrderRequest getOrder(@PathVariable Long orderId) {
        return Converter.convertOrderRequest(orderService.getOrder(orderId));
    }

    @PostMapping("/api/reply-to-order")
    public void replyToOrder(@RequestBody ReplyRequest replyRequest) {
        orderService.replyToOrder(replyRequest);
    }

    @GetMapping("/api/get-order-replies/{orderId}")
    public List<ReplyResponse> replyResponse(@PathVariable Long orderId) {
        return orderService.getOrder(orderId).getReplyList().stream().map(Converter::convertReplyResponse).toList();
    }

    @PostMapping("/api/accept-reply/{replyId}")
    public void acceptReply(@PathVariable Long replyId) {
        orderService.acceptReply(replyId);
    }
}