package bsuir.chernikov.main.controllers;

import bsuir.chernikov.main.dto.CommentDto;
import bsuir.chernikov.main.dto.CourierDto;
import bsuir.chernikov.main.entities.Client;
import bsuir.chernikov.main.service.CourierService;
import bsuir.chernikov.main.service.UserService;
import bsuir.chernikov.main.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class CourierController {
    private final CourierService courierService;
    private final UserService userService;

    @GetMapping("/api/get-courier-from-context")
    public CourierDto getCourierInfo() {
        return Converter.convertCourierToDto(courierService.getCourierFromContext());
    }

    @PutMapping("/api/update-courier-info")
    public void saveCourierInfo(@RequestBody CourierDto courierDto) {
        courierService.updateCourierInfo(courierDto);
    }

    @GetMapping("/api/get-courier")
    public CourierDto getCourier(@RequestParam Long courierId) {
        return Converter.convertCourierToDto(courierService.getCourierById(courierId));
    }
}
