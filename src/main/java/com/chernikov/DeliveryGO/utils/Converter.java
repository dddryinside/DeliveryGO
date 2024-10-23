package com.chernikov.DeliveryGO.utils;

import com.chernikov.DeliveryGO.entities.Address;
import com.chernikov.DeliveryGO.entities.DeliveryOrder;
import com.chernikov.DeliveryGO.enums.ROLE;
import com.chernikov.DeliveryGO.requests.AddressRequest;
import com.chernikov.DeliveryGO.requests.OrderRequest;
import com.chernikov.DeliveryGO.requests.UserRequest;
import com.chernikov.DeliveryGO.security.entities.RegRequest;
import com.chernikov.DeliveryGO.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

public class Converter {
    public static User convertRegRequest(RegRequest request) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();

        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));

        ROLE role = ROLE.fromString(request.getRole());
        if (role != ROLE.ADMIN && role != ROLE.DIRECTOR) {
            user.setRole(ROLE.fromString(request.getRole()));
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Role must be CLIENT or COURIER");
        }

        return user;
    }

    public static OrderRequest convertOrderRequest(DeliveryOrder deliveryOrder) {
        OrderRequest orderRequest = new OrderRequest();

        orderRequest.setName(deliveryOrder.getName());
        orderRequest.setStartPoint(deliveryOrder.getStartPoint().toString());
        orderRequest.setEndPoint(deliveryOrder.getEndPoint().toString());
        orderRequest.setDistance(String.valueOf(deliveryOrder.getDistance()));
        orderRequest.setSize(deliveryOrder.getSize().getName());
        orderRequest.setStatus(deliveryOrder.getStatus().getName());

        return orderRequest;
    }

    public static UserRequest convertUserRequest(User user) {
        UserRequest userRequest = new UserRequest();
        userRequest.setName(userRequest.getName());
        userRequest.setUsername(userRequest.getUsername());
        return userRequest;
    }
}
