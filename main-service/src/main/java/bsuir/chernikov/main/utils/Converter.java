package bsuir.chernikov.main.utils;

import bsuir.chernikov.main.entities.Client;
import bsuir.chernikov.main.entities.Courier;
import bsuir.chernikov.main.entities.User;
import bsuir.chernikov.main.entities.Reply;
import bsuir.chernikov.main.enums.ROLE;
import bsuir.chernikov.main.logging.LogMessage;
import bsuir.chernikov.main.requests.*;
import bsuir.chernikov.main.security.entities.RegRequest;
import bsuir.chernikov.main.entities.DeliveryOrder;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Converter {
    public static User convertRegRequest(RegRequest request) {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        ROLE role = ROLE.fromString(request.getRole());
        if (role != ROLE.CLIENT && role != ROLE.COURIER) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Role must be CLIENT or COURIER");
        }

        User user = switch (role) {
            case CLIENT -> new Client();
            case COURIER -> new Courier();
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid role");
        };

        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setBalance(0);
        user.setRole(role);

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
        orderRequest.setCourier(deliveryOrder.getCourier().getName());

        return orderRequest;
    }

    public static ReplyResponse convertReplyResponse(Reply reply) {
        ReplyResponse replyResponse = new ReplyResponse();
        Courier courier = reply.getCourier();
        replyResponse.setReplyId(reply.getId());
        replyResponse.setCourierId(courier.getId());
        replyResponse.setCourierName(courier.getName());
        replyResponse.setCourierRating(10);
        replyResponse.setPrice(reply.getPrice());
        return replyResponse;
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

    public static LogMessage createLog(String type, String level, String message) {
        LogMessage logMessage = new LogMessage();
        logMessage.setType(type);
        logMessage.setLevel(level);
        logMessage.setMessage(message);
        logMessage.setTime(LocalDateTime.now());
        return logMessage;
    }
}
