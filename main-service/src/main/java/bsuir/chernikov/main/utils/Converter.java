package bsuir.chernikov.main.utils;

import bsuir.chernikov.main.entities.*;
import bsuir.chernikov.main.enums.ROLE;
import bsuir.chernikov.main.dto.*;
import bsuir.chernikov.main.security.entities.RegRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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

    public static UserDto convertUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(Math.toIntExact(user.getId()));
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    public static ResponseWrapper<FeedbackDto> convertFeedbacksList(Page<Feedback> feedbackPage) {
        List<FeedbackDto> feedbackDtoList = new ArrayList<>();
        for (Feedback feedback : feedbackPage.getContent()) {
            FeedbackDto feedbackDto = new FeedbackDto();
            feedbackDto.setId(Math.toIntExact(feedback.getId()));
            feedbackDto.setUsername(feedback.getUser().getUsername());
            feedbackDto.setUserRole(feedback.getUser().getRole().name());
            feedbackDto.setDateTime(formatLocalDateTime(feedback.getDateTime()));
            feedbackDto.setMessage(feedback.getContent());
            feedbackDtoList.add(feedbackDto);
        }
        ResponseWrapper<FeedbackDto> responseWrapper = new ResponseWrapper<>();
        responseWrapper.setObjects(feedbackDtoList);
        responseWrapper.setFoundAll(Math.toIntExact(feedbackPage.getTotalElements()));
        responseWrapper.setPagesAll(Math.toIntExact(feedbackPage.getTotalPages()));
        return responseWrapper;
    }

    public static ResponseWrapper<AddressDto> convertAddressList(Page<Address> addressPage) {
        List<AddressDto> addressDtoList = new ArrayList<>();
        for (Address address : addressPage.getContent()) {
            AddressDto addressDto = new AddressDto();
            addressDto.setId(Math.toIntExact(address.getId()));
            addressDto.setName(address.getName());
            addressDto.setCountry(address.getCountry());
            addressDto.setCity(address.getCity());
            addressDto.setAddress(address.getAddress());
            addressDto.setAdditional(address.getAdditional());
            addressDtoList.add(addressDto);
        }
        ResponseWrapper<AddressDto> responseWrapper = new ResponseWrapper<>();
        responseWrapper.setObjects(addressDtoList);
        responseWrapper.setFoundAll(Math.toIntExact(addressPage.getTotalElements()));
        responseWrapper.setPagesAll(Math.toIntExact(addressPage.getTotalPages()));
        return responseWrapper;
    }
}
