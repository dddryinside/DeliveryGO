package com.chernikov.DeliveryGO.repository;

import com.chernikov.DeliveryGO.entities.DeliveryOrder;
import com.chernikov.DeliveryGO.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<DeliveryOrder, Long> {
    List<DeliveryOrder> findAllByClientIs(User user);
}
