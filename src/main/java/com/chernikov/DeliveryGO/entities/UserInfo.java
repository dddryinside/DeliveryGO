package com.chernikov.DeliveryGO.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class UserInfo {
    @Id
    private Long id;

    @OneToMany
    private List<Address> addressList = new ArrayList<>();

    @OneToMany
    private List<Delivery> deliveryList = new ArrayList<>();
}
