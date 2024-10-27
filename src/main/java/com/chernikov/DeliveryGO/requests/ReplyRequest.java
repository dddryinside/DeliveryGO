package com.chernikov.DeliveryGO.requests;

import lombok.Data;

@Data
public class ReplyRequest {
    private String orderId;
    private String price;
}
