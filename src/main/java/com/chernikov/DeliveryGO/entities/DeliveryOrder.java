package com.chernikov.DeliveryGO.entities;

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

    private String startPoint;
    private String endPoint;

    private Integer length;
    private Integer price;
    private Integer time;
    private LocalDateTime created;

    @ManyToOne
    private User client;

}