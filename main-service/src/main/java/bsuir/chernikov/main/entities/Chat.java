package bsuir.chernikov.main.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private DeliveryOrder order;
}
