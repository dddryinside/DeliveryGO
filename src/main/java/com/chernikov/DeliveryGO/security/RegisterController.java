package com.chernikov.DeliveryGO.security;

import com.chernikov.DeliveryGO.security.entities.RegRequest;
import com.chernikov.DeliveryGO.security.entities.User;
import com.chernikov.DeliveryGO.service.UserService;
import com.chernikov.DeliveryGO.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;

    @PostMapping("/api/register-user")
    public ResponseEntity<HttpStatus> registerUser(@RequestBody RegRequest request) {
        try {
            User newUser = Converter.convertRegRequest(request);
            userService.saveUser(newUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
