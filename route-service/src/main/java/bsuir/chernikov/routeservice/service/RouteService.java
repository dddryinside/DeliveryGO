package bsuir.chernikov.routeservice.service;

import bsuir.chernikov.routeservice.dto.RouteDto;
import bsuir.chernikov.routeservice.entities.Address;
import bsuir.chernikov.routeservice.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final AddressRepository addressRepository;
    private final YandexDistanceService yandexDistanceService;

    private Address startAddress;
    private Address endAddress;

    public RouteDto calculateRoute(RouteDto routeDto) {
        initializeService(routeDto);

        RouteDto response = new RouteDto();
        response.setDistance(calculateDistance(startAddress, endAddress));
        return response;
    }

    private void initializeService(RouteDto routeDto) {
        Optional<Address> startAddressOptional = addressRepository.findById(Long.valueOf(routeDto.getStartPointId()));
        startAddressOptional.ifPresent(address -> startAddress = address);

        Optional<Address> endAddressOptional = addressRepository.findById(Long.valueOf(routeDto.getEndPointId()));
        endAddressOptional.ifPresent(address -> endAddress = address);
    }

    public Double calculateDistance(Address startAddress, Address endAddress) {
        return yandexDistanceService.getDistance(startAddress.toString(), endAddress.toString());
    }
}
