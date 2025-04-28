package bsuir.chernikov.main.repository;

import bsuir.chernikov.main.entities.DeliveryOrder;
import bsuir.chernikov.main.entities.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    void deleteAllByOrder(DeliveryOrder deliveryOrder);
}
