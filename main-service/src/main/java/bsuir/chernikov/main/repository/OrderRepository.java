package bsuir.chernikov.main.repository;

import bsuir.chernikov.main.entities.Address;
import bsuir.chernikov.main.entities.Client;
import bsuir.chernikov.main.entities.DeliveryOrder;
import bsuir.chernikov.main.enums.ORDER_STATUS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<DeliveryOrder, Long> {
    List<DeliveryOrder> findAllByClientAndStatus(Client client, ORDER_STATUS order_status);

    List<DeliveryOrder> findAllByStatus(ORDER_STATUS order_status);

    List<DeliveryOrder> findAllByStatusInAndStartPoint(List<ORDER_STATUS> orderStatusList, Address startPoint);

    List<DeliveryOrder> findAllByStatusInAndEndPoint(List<ORDER_STATUS> orderStatusList, Address endPoint);
}
