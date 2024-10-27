package com.chernikov.DeliveryGO.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private DeliveryOrder order;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;

    private LocalDateTime dateTime;

    private Integer price;
}
