package com.chernikov.DeliveryGO.service;

import com.chernikov.DeliveryGO.entities.DeliveryOrder;
import com.chernikov.DeliveryGO.entities.User;
import com.chernikov.DeliveryGO.repository.OrderRepository;
import com.chernikov.DeliveryGO.requests.OrderRequest;
import com.chernikov.DeliveryGO.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    public DeliveryOrder createOrder(OrderRequest orderRequest) {
        RouteService routeService = new RouteService(
                orderRequest.getStartPoint(), orderRequest.getEndPoint());
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setName(orderRequest.getName());
        deliveryOrder.setLength(routeService.getRouteDistance());
        deliveryOrder.setTime(routeService.getRouteTime());
        deliveryOrder.setPrice(routeService.getRoutePrice());
        deliveryOrder.setCreated(LocalDateTime.now());
        return orderRepository.save(deliveryOrder);
    }

    public List<OrderRequest> getOrdersByClientId(Long clientId) {
        User user = userService.getUser(clientId);
        List<OrderRequest> orderRequestList = new ArrayList<>();
        for (DeliveryOrder order : orderRepository.findAllByClientIs(user)) {
            orderRequestList.add(Converter.convertOrderRequest(order));
        }
        return orderRequestList;
    }
}
