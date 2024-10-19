package com.chernikov.DeliveryGO.controllers;

import com.chernikov.DeliveryGO.security.entities.RegRequest;
import com.chernikov.DeliveryGO.entities.User;
import com.chernikov.DeliveryGO.service.UserService;
import com.chernikov.DeliveryGO.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final UserService userService;

    @PostMapping(value = {"/api/register-user"})
    public ResponseEntity<User> registerUser(@RequestBody RegRequest request) {
        return new ResponseEntity<>(userService.saveUser(Converter.convertRegRequest(request)), HttpStatus.OK);
    }
}