package com.chernikov.DeliveryGO.service;

import com.chernikov.DeliveryGO.entities.Address;
import com.chernikov.DeliveryGO.entities.Client;
import com.chernikov.DeliveryGO.entities.User;
import com.chernikov.DeliveryGO.repository.AddressRepository;
import com.chernikov.DeliveryGO.requests.AddressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserService userService;


    public void saveAddress(AddressRequest addressRequest) {
        if (userService.getUserFromContext() instanceof Client client) {
            Address address = new Address();
            address.setName(addressRequest.getName());
            address.setCity(addressRequest.getCity());
            address.setAddress(addressRequest.getAddress());
            address.setClient(client);
            addressRepository.save(address);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }


    public void deleteAddress(Long addressId) {
        Optional<Address> addressOptional = addressRepository.findById(addressId);
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            if (userService.getUserFromContext() instanceof Client client) {
                if (client.getId().equals(addressId)) addressRepository.delete(address);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Address getAddressById(Long addressId) {
        Optional<Address> addressOptional = addressRepository.findById(addressId);
        if (addressOptional.isPresent()) {
            return addressOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address is not found by id = " + addressId);
        }
    }
}
