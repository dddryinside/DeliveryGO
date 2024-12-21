package bsuir.chernikov.routeservice.repository;

import bsuir.chernikov.routeservice.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findById(Long id);
}
