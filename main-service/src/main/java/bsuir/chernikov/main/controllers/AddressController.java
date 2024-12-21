package bsuir.chernikov.main.controllers;

import bsuir.chernikov.main.dto.ResponseWrapper;
import bsuir.chernikov.main.entities.Address;
import bsuir.chernikov.main.entities.Client;
import bsuir.chernikov.main.dto.AddressDto;
import bsuir.chernikov.main.service.AddressService;
import bsuir.chernikov.main.service.UserService;
import bsuir.chernikov.main.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final UserService userService;

    @GetMapping(value = {"/api/get-client-address-list"})
    public ResponseWrapper<AddressDto> getClientAddressList() {
        if (userService.getUserFromContext() instanceof Client client) {
            return Converter.convertAddressList(addressService
                    .getClientAddressList(client, PageRequest.of(0, 15)));
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "/api/get-address/{addressId}")
    public Address getAddress(@PathVariable Long addressId) {
        return addressService.getAddressById(addressId);
    }

    @PostMapping(value = {"/api/save-address"})
    public void saveAddress(@RequestBody AddressDto addressRequest) {
        addressService.saveAddress(addressRequest);
    }

    @DeleteMapping("/api/delete-address/{addressId}")
    public void deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
    }
}