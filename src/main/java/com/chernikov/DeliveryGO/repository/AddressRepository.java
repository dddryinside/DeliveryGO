package com.chernikov.DeliveryGO.repository;

import com.chernikov.DeliveryGO.entities.Address;
import com.chernikov.DeliveryGO.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByUser(User user);
}
