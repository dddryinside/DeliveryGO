package com.chernikov.DeliveryGO.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Courier extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "courier")
    private List<Reply> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "courier")
    private List<DeliveryOrder> orderList = new ArrayList<>();
}