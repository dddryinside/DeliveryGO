package com.chernikov.DeliveryGO.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Request DTO for Address")
public class AddressRequest {

    @Schema(description = "Name of the address", example = "Home")
    private String name;

    @Schema(description = "City of the address", example = "New York")
    private String city;

    @Schema(description = "Full address", example = "123 Main St")
    private String address;
}