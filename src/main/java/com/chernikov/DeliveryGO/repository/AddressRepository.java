package com.chernikov.DeliveryGO.repository;

import com.chernikov.DeliveryGO.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
