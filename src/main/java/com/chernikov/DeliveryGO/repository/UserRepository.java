package com.chernikov.DeliveryGO.repository;

import com.chernikov.DeliveryGO.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
