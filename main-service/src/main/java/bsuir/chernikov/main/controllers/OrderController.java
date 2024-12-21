package bsuir.chernikov.main.controllers;

import bsuir.chernikov.main.dto.OrderDto;
import bsuir.chernikov.main.dto.ReplyRequest;
import bsuir.chernikov.main.dto.ReplyResponse;
import bsuir.chernikov.main.enums.ORDER_STATUS;
import bsuir.chernikov.main.service.OrderService;
import bsuir.chernikov.main.utils.Converter;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/api/create-order")
    public Integer createOrder(@RequestBody OrderDto orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @DeleteMapping("/api/delete-order/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }

    @GetMapping("/api/get-client-orders")
    public List<OrderDto> getOrders(@RequestParam (required = false) String sortByOrderStatus) {
        return orderService
                .getClientOrders(Optional.of(ORDER_STATUS.fromString(sortByOrderStatus)).orElse(ORDER_STATUS.CREATED))
                .stream().map(Converter::convertOrderRequest).toList();
    }

    @GetMapping("api/get-order/{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) {
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