package com.chernikov.DeliveryGO.service;

import com.chernikov.DeliveryGO.entities.DeliveryOrder;
import com.chernikov.DeliveryGO.entities.User;
import com.chernikov.DeliveryGO.enums.ORDER_STATUS;
import com.chernikov.DeliveryGO.enums.SIZE;
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
    private final AddressService addressService;

    public DeliveryOrder createOrder(OrderRequest orderRequest) {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setName(orderRequest.getName());

        deliveryOrder.setStartPoint(addressService.getAddressById(Long.valueOf(orderRequest.getStartPoint())));
        deliveryOrder.setEndPoint(addressService.getAddressById(Long.valueOf(orderRequest.getEndPoint())));

        deliveryOrder.setSize(SIZE.fromString(orderRequest.getSize()));
        deliveryOrder.setDistance(Double.valueOf(orderRequest.getDistance()));
        deliveryOrder.setStatus(ORDER_STATUS.CREATED);

        deliveryOrder.setClient(userService.getUserFromContext());
        deliveryOrder.setCreated(LocalDateTime.now());

        return orderRepository.save(deliveryOrder);
    }
}
