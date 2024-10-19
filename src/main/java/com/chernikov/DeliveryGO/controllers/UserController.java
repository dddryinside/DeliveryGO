package com.chernikov.DeliveryGO.controllers;

import com.chernikov.DeliveryGO.entities.User;
import com.chernikov.DeliveryGO.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/api/get-user")
    public ResponseEntity<User> getUser() {
        return new ResponseEntity<>(userService.getUserFromContext(), HttpStatus.OK);
    }
}
