package com.chernikov.DeliveryGO.controllers;

import com.chernikov.DeliveryGO.entities.Address;
import com.chernikov.DeliveryGO.requests.AddressRequest;
import com.chernikov.DeliveryGO.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping(value = {"/api/save-address"})
    public ResponseEntity<Address> saveAddress(@ModelAttribute AddressRequest addressRequest) {
        return new ResponseEntity<>(addressService.saveAddress(addressRequest), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/api/delete-address"})
    public ResponseEntity<HttpStatus> deleteAddress(@RequestParam Long addressId) {
        addressService.deleteAddress(addressId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}