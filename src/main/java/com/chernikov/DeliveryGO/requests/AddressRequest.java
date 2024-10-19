package com.chernikov.DeliveryGO.requests;

import lombok.Data;

@Data
public class AddressRequest {
    private String name;
    private String city;
    private String address;
}
