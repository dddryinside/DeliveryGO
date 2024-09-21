package com.chernikov.DeliveryGO.utils;

import com.chernikov.DeliveryGO.security.entities.RegRequest;
import com.chernikov.DeliveryGO.security.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Converter {
    public static User convertRegRequest(RegRequest request) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();

        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        return user;
    }
}
