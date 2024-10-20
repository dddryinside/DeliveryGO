package com.chernikov.DeliveryGO.service;

import com.chernikov.DeliveryGO.entities.Address;
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
        Address address = new Address();
        address.setName(addressRequest.getName());
        address.setCity(addressRequest.getCity());
        address.setAddress(addressRequest.getAddress());
        address.setUser(userService.getUserFromContext());
        addressRepository.save(address);
    }


    public void deleteAddress(Long addressId) {
        Optional<Address> addressOptional = addressRepository.findById(addressId);
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            if (userService.getUserFromContext().getId().equals(address.getUser().getId())) {
                addressRepository.delete(address);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    public List<Address> getUserAddressList(User user) {
        return addressRepository.findAllByUser(user);
    }
}
