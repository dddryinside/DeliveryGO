package com.chernikov.DeliveryGO.entities;

import com.chernikov.DeliveryGO.security.entities.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class CourierInfo {
    @Id
    private Long id;

    @OneToOne
    private User courier;

    @OneToMany
    private List<Delivery> deliveryList = new ArrayList<>();
}
