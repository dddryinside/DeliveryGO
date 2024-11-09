package bsuir.chernikov.main.controllers;

import bsuir.chernikov.main.requests.OrderRequest;
import bsuir.chernikov.main.requests.ReplyRequest;
import bsuir.chernikov.main.requests.ReplyResponse;
import bsuir.chernikov.main.service.OrderService;
import bsuir.chernikov.main.utils.Converter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Create a new order")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid order data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/api/create-order")
    public void createOrder(@RequestBody OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
    }

    @Operation(summary = "Delete an order by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/api/delete-order/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }

    @Operation(summary = "Get list of orders")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved orders"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/api/get-orders")
    public List<OrderRequest> getOrders() {
        return orderService.getOrders().stream().map(Converter::convertOrderRequest).toList();
    }

    @Operation(summary = "Get an order by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("api/get-order/{orderId}")
    public OrderRequest getOrder(@PathVariable Long orderId) {
        return Converter.convertOrderRequest(orderService.getOrder(orderId));
    }

    @Operation(summary = "Reply to an order")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reply sent successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid reply data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/api/reply-to-order")
    public void replyToOrder(@RequestBody ReplyRequest replyRequest) {
        orderService.replyToOrder(replyRequest);
    }

    @Operation(summary = "Get replies for an order by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved replies"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/api/get-order-replies/{orderId}")
    public List<ReplyResponse> replyResponse(@PathVariable Long orderId) {
        return orderService.getOrder(orderId).getReplyList().stream().map(Converter::convertReplyResponse).toList();
    }

    @Operation(summary = "Accept a reply by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reply accepted successfully"),
            @ApiResponse(responseCode = "404", description = "Reply not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/api/accept-reply/{replyId}")
    public void acceptReply(@PathVariable Long replyId) {
        orderService.acceptReply(replyId);
    }
}