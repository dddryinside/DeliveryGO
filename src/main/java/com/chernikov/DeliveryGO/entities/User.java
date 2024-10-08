package com.chernikov.DeliveryGO.entities;

import com.chernikov.DeliveryGO.enums.ROLE;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ROLE role;
    private String name;
    private String username;
    private String password;
    private Integer balance;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Address> addressList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SupportMessage> supportSupportMessageList = new ArrayList<>();
}