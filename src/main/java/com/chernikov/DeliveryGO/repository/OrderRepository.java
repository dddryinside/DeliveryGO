package com.chernikov.DeliveryGO.repository;

import com.chernikov.DeliveryGO.entities.DeliveryOrder;
import com.chernikov.DeliveryGO.enums.ORDER_STATUS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<DeliveryOrder, Long> {
    List<DeliveryOrder> findAllByStatusIs(ORDER_STATUS order_status);
}
