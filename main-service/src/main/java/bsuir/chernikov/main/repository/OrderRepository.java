package bsuir.chernikov.main.repository;

import bsuir.chernikov.main.entities.Courier;
import bsuir.chernikov.main.entities.DeliveryOrder;
import bsuir.chernikov.main.enums.ORDER_STATUS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<DeliveryOrder, Long> {
    List<DeliveryOrder> findAllByStatusIs(ORDER_STATUS order_status);
    @Query("SELECT o FROM DeliveryOrder o WHERE o.status = :status AND o NOT IN (SELECT r.order FROM Reply r WHERE r.courier = :courier)")
    List<DeliveryOrder> findAvailableOrdersForCourier(@Param("status") ORDER_STATUS status, @Param("courier") Courier courier);
}
