package com.chernikov.DeliveryGO.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Parcel {
    @Id
    private Long id;

    private Integer length;
    private Integer width;
    private Integer height;

    private Integer weight;
}
