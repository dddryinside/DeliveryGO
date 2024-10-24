package com.chernikov.DeliveryGO.requests;

import lombok.Data;

@Data
public class OrderRequest {
    private Long id;
    private String name;
    private String startPoint;
    private String endPoint;
    private String distance;
    private String size;
    private String status;
    private String createdAt;
}
