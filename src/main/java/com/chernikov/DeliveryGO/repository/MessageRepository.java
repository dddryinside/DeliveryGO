package com.chernikov.DeliveryGO.repository;

import com.chernikov.DeliveryGO.entities.SupportMessage;
import com.chernikov.DeliveryGO.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<SupportMessage, Long> {
    List<SupportMessage> findAllByUser(User user, Pageable pageable);
}
