package com.chernikov.DeliveryGO.controllers;

import com.chernikov.DeliveryGO.entities.Address;
import com.chernikov.DeliveryGO.entities.Client;
import com.chernikov.DeliveryGO.requests.AddressRequest;
import com.chernikov.DeliveryGO.service.AddressService;
import com.chernikov.DeliveryGO.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final UserService userService;

    @Operation(summary = "Get user address list")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully"),
            @ApiResponse(responseCode = "500", description = "Server error, something wrong")
    })
    @GetMapping(value = {"/api/get-user-address-list"})
    public List<Address> getUserAddressList() {
        if (userService.getUserFromContext() instanceof Client client) {
            return client.getAddressList();
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Operation(summary = "Get address by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Server error, something wrong")
    })
    @GetMapping(value = "/api/get-address/{addressId}")
    public Address getAddress(@PathVariable Long addressId) {
        return addressService.getAddressById(addressId);
    }

    @Operation(summary = "Save address")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully"),
            @ApiResponse(responseCode = "500", description = "Server error, something wrong")
    })
    @PostMapping(value = {"/api/save-address"})
    public void saveAddress(@ModelAttribute AddressRequest addressRequest) {
        addressService.saveAddress(addressRequest);
    }

    @Operation(summary = "Delete address")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Server error, something wrong")
    })
    @DeleteMapping("/api/delete-address/{addressId}")
    public void deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
    }
}