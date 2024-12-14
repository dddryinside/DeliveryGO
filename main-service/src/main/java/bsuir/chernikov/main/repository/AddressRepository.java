package bsuir.chernikov.main.repository;

import bsuir.chernikov.main.entities.Address;
import bsuir.chernikov.main.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Page<Address> getAddressesByClient(Client client, Pageable pageable);
}
