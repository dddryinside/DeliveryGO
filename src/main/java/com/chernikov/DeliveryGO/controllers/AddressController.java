package com.chernikov.DeliveryGO.controllers;

import com.chernikov.DeliveryGO.entities.Address;
import com.chernikov.DeliveryGO.requests.AddressRequest;
import com.chernikov.DeliveryGO.service.AddressService;
import com.chernikov.DeliveryGO.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    private final UserService userService;

    @GetMapping(value = {"/api/get-user-address-list"})
    public List<Address> getUserAddressList() {
        return addressService.getUserAddressList(userService.getUserFromContext());
    }

    @GetMapping(value = "/api/get-address/{addressId}")
    public Address getAddress(@PathVariable Long addressId) {
        return addressService.getAddressById(addressId);
    }

    @PostMapping(value = {"/api/save-address"})
    public ResponseEntity<HttpStatus> saveAddress(@ModelAttribute AddressRequest addressRequest) {
        addressService.saveAddress(addressRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/delete-address/{addressId}")
    public ResponseEntity<HttpStatus> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}