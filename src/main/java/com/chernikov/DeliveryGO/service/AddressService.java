package com.chernikov.DeliveryGO.service;

import com.chernikov.DeliveryGO.entities.Address;
import com.chernikov.DeliveryGO.repository.AddressRepository;
import com.chernikov.DeliveryGO.requests.AddressRequest;
import com.chernikov.DeliveryGO.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserService userService;


    public Address saveAddress(AddressRequest addressRequest) {
        return addressRepository.save(Converter.convertAddressRequest(addressRequest));
    }


    public void deleteAddress(Long addressId) {
        Optional<Address> addressOptional = addressRepository.findById(addressId);
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            if (userService.getUser().getId().equals(address.getUser().getId())) {
                addressRepository.delete(address);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
