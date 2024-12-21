package bsuir.chernikov.main.service;

import bsuir.chernikov.main.entities.Address;
import bsuir.chernikov.main.entities.Client;
import bsuir.chernikov.main.repository.AddressRepository;
import bsuir.chernikov.main.dto.AddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserService userService;

    public void saveAddress(AddressDto addressRequest) {
        if (userService.getUserFromContext() instanceof Client client) {
            Address address = new Address();
            address.setName(addressRequest.getName());
            address.setCountry(addressRequest.getCountry());
            address.setCity(addressRequest.getCity());
            address.setAddress(addressRequest.getAddress());
            address.setCoordinates(addressRequest.getCoordinates()
                    .stream().map(String::valueOf).collect(Collectors.joining("; ")));
            address.setAdditional(addressRequest.getAdditional());
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
                if (client.getId().equals(address.getClient().getId())) {
                    addressRepository.delete(address);
                } else {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }
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

    public Page<Address> getClientAddressList(Client client, Pageable pageable) {
        return addressRepository.findAllByClient(client, pageable);
    }
}
