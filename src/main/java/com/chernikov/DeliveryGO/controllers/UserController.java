package com.chernikov.DeliveryGO.controllers;

import com.chernikov.DeliveryGO.requests.UserRequest;
import com.chernikov.DeliveryGO.service.UserService;
import com.chernikov.DeliveryGO.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/api/get-user")
    public ResponseEntity<UserRequest> getUser(@RequestParam Long userId) {
        return new ResponseEntity<>(Converter.convertUserRequest(userService.getUser(userId)), HttpStatus.OK);
    }
}
