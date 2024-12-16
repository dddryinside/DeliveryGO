package bsuir.chernikov.main.repository;

import bsuir.chernikov.main.entities.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository extends JpaRepository<Courier, Long> {
}
