package com.chernikov.DeliveryGO.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer price;
}