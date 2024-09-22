package com.chernikov.DeliveryGO.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    private Long id;

    private String name;
    private String city;
    private String address;
    private String apartment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}