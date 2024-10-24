package com.chernikov.DeliveryGO.utils;

import com.chernikov.DeliveryGO.entities.DeliveryOrder;
import com.chernikov.DeliveryGO.enums.ROLE;
import com.chernikov.DeliveryGO.requests.OrderRequest;
import com.chernikov.DeliveryGO.requests.UserRequest;
import com.chernikov.DeliveryGO.security.entities.RegRequest;
import com.chernikov.DeliveryGO.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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

        orderRequest.setId(deliveryOrder.getId());
        orderRequest.setName(deliveryOrder.getName());
        orderRequest.setStartPoint(deliveryOrder.getStartPoint().toString());
        orderRequest.setEndPoint(deliveryOrder.getEndPoint().toString());
        orderRequest.setDistance(String.valueOf(deliveryOrder.getDistance()));
        orderRequest.setSize(deliveryOrder.getSize().getName());
        orderRequest.setStatus(deliveryOrder.getStatus().getName());
        orderRequest.setCreatedAt(formatLocalDateTime(deliveryOrder.getCreated()));

        return orderRequest;
    }

    public static String formatLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss")
                .withLocale(Locale.ENGLISH);
        return dateTime.format(formatter);
    }

    public static Double convertAndRound(String input) {
        try {
            double value = Double.parseDouble(input);
            value = Math.round(value * 10) / 10.0;
            return value;
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Distance value is not correct: " + input);
        }
    }

    public static UserRequest convertUserRequest(User user) {
        UserRequest userRequest = new UserRequest();
        userRequest.setName(userRequest.getName());
        userRequest.setUsername(userRequest.getUsername());
        return userRequest;
    }
}
