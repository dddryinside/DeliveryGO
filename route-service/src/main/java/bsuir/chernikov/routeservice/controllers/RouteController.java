package bsuir.chernikov.routeservice.controllers;

import bsuir.chernikov.routeservice.dto.RouteDto;
import bsuir.chernikov.routeservice.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @PostMapping("/route-service-api/calculate-route")
    public RouteDto calculateRoute(@RequestBody RouteDto routeDto) {
        return routeService.calculateRoute(routeDto);
    }
}
