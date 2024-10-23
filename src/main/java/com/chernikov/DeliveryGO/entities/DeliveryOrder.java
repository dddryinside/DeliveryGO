package com.chernikov.DeliveryGO.entities;

import com.chernikov.DeliveryGO.enums.ORDER_STATUS;
import com.chernikov.DeliveryGO.enums.SIZE;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Address startPoint;

    @ManyToOne
    private Address endPoint;

    private SIZE size;

    private Double distance;

    private ORDER_STATUS status;

    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private User client;
}