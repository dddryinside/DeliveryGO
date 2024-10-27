package com.chernikov.DeliveryGO.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "courier")
    private List<Reply> replyList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "courier")
    private List<DeliveryOrder> orderList = new ArrayList<>();
}