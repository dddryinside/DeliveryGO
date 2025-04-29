package bsuir.chernikov.main.controllers;

import bsuir.chernikov.main.dto.OrderDto;
import bsuir.chernikov.main.dto.OrderReplyDto;
import bsuir.chernikov.main.entities.Courier;
import bsuir.chernikov.main.enums.ORDER_STATUS;
import bsuir.chernikov.main.service.OrderService;
import bsuir.chernikov.main.service.UserService;
import bsuir.chernikov.main.utils.Converter;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/api/create-order")
    public Integer createOrder(@RequestBody OrderDto orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @PostMapping("/api/cancel-order")
    public Boolean deleteOrder(@RequestParam Long orderId) {
        return orderService.cancelOrder(orderId);
    }

    @GetMapping("/api/get-client-orders")
    public List<OrderDto> getOrders(@RequestParam (required = false) String filterByOrderStatus) {
        return orderService
                .getClientOrders(Optional.of(ORDER_STATUS.fromString(filterByOrderStatus)).orElse(ORDER_STATUS.CREATED))
                .stream().map(Converter::convertOrderRequest).toList();
    }

    @GetMapping("api/get-order/{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) {
        return Converter.convertOrderRequest(orderService.getOrder(orderId));
    }

    @GetMapping("/api/get-order-reply-permission")
    public Boolean getOrderReplyPermission() {
        return userService.getUserFromContext() instanceof Courier;
    }

    @PostMapping("/api/reply-to-order")
    public void replyToOrder(@RequestBody OrderReplyDto replyDto) {
        orderService.replyToOrder(replyDto);
    }

    @GetMapping("/api/get-order-replies")
    public List<OrderReplyDto> replyResponse(@RequestParam Long orderId) {
        return orderService.getOrder(orderId).getReplyList().stream().map(Converter::convertReplyResponse).toList();
    }

    @GetMapping("/api/accept-reply")
    public void acceptReply(@RequestParam Long replyId) {
        System.out.println(replyId);
        orderService.acceptReply(replyId);
    }

    @GetMapping("/api/get-available-orders")
    public List<OrderDto> getAvailableOrders(@RequestParam(required = false) String geo) {
        return orderService.getAvailableOrders(geo).stream().map(Converter::convertOrderRequest).toList();
    }

    @GetMapping("/api/get-couriers-orders-in-work")
    public List<OrderDto> getCouriersOrdersInWork() {
        return orderService.getCouriersOrdersInWork().stream().map(Converter::convertOrderRequest).toList();
    }

    @GetMapping("/api/complete-order")
    public void completeOrder(@RequestParam Long orderId) {
        orderService.completeOrder(orderId);
    }
}