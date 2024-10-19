package com.chernikov.DeliveryGO.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}