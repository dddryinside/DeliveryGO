package bsuir.chernikov.main.controllers;

import bsuir.chernikov.main.enums.CARGO_TYPE;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class EnumController {
    @GetMapping("/api/get-cargo-types")
    public List<String> getCargoTypes() {
        return Arrays.stream(CARGO_TYPE.values()).map(CARGO_TYPE::getName).toList();
    }
}
