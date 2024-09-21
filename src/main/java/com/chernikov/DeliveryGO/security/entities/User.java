package com.chernikov.DeliveryGO.security.entities;

import com.chernikov.DeliveryGO.enums.ROLE;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    private Long id;

    private ROLE role;
    private String name;
    private String username;
    private String password;
    private Integer balance;

    @OneToOne
    private User user;

    @OneToOne
    private User courier;
}