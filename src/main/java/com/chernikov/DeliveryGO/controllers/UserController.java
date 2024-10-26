package com.chernikov.DeliveryGO.controllers;

import com.chernikov.DeliveryGO.entities.User;
import com.chernikov.DeliveryGO.security.entities.RegRequest;
import com.chernikov.DeliveryGO.service.UserService;
import com.chernikov.DeliveryGO.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(value = {"/api/registrate-user"})
    public String registerUser(@ModelAttribute RegRequest request) {
        userService.saveUser(Converter.convertRegRequest(request));
        return "redirect:/user.html";
    }

    @GetMapping("/api/get-user")
    public ResponseEntity<User> getUser() {
        return new ResponseEntity<>(userService.getUserFromContext(), HttpStatus.OK);
    }

    @PostMapping("/api/top-up-balance")
    public void topUpBalance(@RequestParam Integer value) {
        userService.topUpBalance(userService.getUserFromContext(), value);
    }
}
