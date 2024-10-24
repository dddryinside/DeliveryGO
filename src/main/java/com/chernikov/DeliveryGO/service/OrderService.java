package com.chernikov.DeliveryGO.service;

import com.chernikov.DeliveryGO.entities.DeliveryOrder;
import com.chernikov.DeliveryGO.enums.ORDER_STATUS;
import com.chernikov.DeliveryGO.enums.SIZE;
import com.chernikov.DeliveryGO.repository.OrderRepository;
import com.chernikov.DeliveryGO.requests.OrderRequest;
import com.chernikov.DeliveryGO.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final AddressService addressService;

    public void createOrder(OrderRequest orderRequest) {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setName(orderRequest.getName());

        deliveryOrder.setStartPoint(addressService.getAddressById(Long.valueOf(orderRequest.getStartPoint())));
        deliveryOrder.setEndPoint(addressService.getAddressById(Long.valueOf(orderRequest.getEndPoint())));

        deliveryOrder.setSize(SIZE.fromString(orderRequest.getSize()));
        deliveryOrder.setDistance(Converter.convertAndRound(orderRequest.getDistance()));
        deliveryOrder.setStatus(ORDER_STATUS.CREATED);

        deliveryOrder.setClient(userService.getUserFromContext());
        deliveryOrder.setCreated(LocalDateTime.now());

        orderRepository.save(deliveryOrder);
    }

    public void deleteOrder(Long orderId) {
        Optional<DeliveryOrder> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            orderRepository.deleteById(orderId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
