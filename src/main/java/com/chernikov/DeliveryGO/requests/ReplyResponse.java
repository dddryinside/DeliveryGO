package com.chernikov.DeliveryGO.requests;

import lombok.Data;

@Data
public class ReplyResponse {
    private Long courierId;
    private String courierName;
    private Integer courierRating;
    private Integer price;
}
