package com.chernikov.DeliveryGO.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
}
