package com.chernikov.DeliveryGO.controllers;

import com.chernikov.DeliveryGO.security.entities.RegRequest;
import com.chernikov.DeliveryGO.service.UserService;
import com.chernikov.DeliveryGO.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final UserService userService;

    @PostMapping(value = {"/api/registrate-user"})
    public String registerUser(@ModelAttribute RegRequest request) {
        userService.saveUser(Converter.convertRegRequest(request));
        return "redirect:/user.html";
    }
}