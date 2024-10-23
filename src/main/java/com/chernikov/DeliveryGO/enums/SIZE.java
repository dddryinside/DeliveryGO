package com.chernikov.DeliveryGO.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
@RequiredArgsConstructor
public enum SIZE {
    SMALL("Small"),
    MIDDLE("Middle"),
    LARGE("Large");

    private final String name;

    public static SIZE fromString(String name) {
        for (SIZE size : SIZE.values()) {
            if (size.name().equalsIgnoreCase(name)) {
                return size;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Size is not correct " + name);
    }
}