package bsuir.chernikov.main.repository;

import bsuir.chernikov.main.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
