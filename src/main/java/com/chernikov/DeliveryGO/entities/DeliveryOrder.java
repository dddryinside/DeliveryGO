package com.chernikov.DeliveryGO.entities;

import com.chernikov.DeliveryGO.enums.ORDER_STATUS;
import com.chernikov.DeliveryGO.enums.SIZE;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "start_address_id")
    private Address startPoint;

    @ManyToOne
    @JoinColumn(name = "end_address_id")
    private Address endPoint;

    private Double distance;

    private SIZE size;

    private ORDER_STATUS status;

    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "order")
    private List<Reply> replyList;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;
}