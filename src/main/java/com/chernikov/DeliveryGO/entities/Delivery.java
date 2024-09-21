package com.chernikov.DeliveryGO.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Delivery {
    @Id
    private Long id;

    private Integer price;
    private Integer deliveryPrice;

    @ManyToOne
    private UserInfo client;

    @ManyToOne
    private UserInfo courier;

    @OneToOne
    private Address startingPoint;

    @OneToOne
    private Address endPoint;

    @OneToOne
    private Parcel parcel;
}
