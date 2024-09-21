package com.chernikov.DeliveryGO.repository;

import com.chernikov.DeliveryGO.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
