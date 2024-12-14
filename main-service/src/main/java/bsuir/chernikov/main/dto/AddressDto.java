package bsuir.chernikov.main.dto;

import lombok.Data;

@Data
public class AddressDto {
    private Integer id;
    private String name;
    private String country;
    private String city;
    private String address;
    private String additional;
}