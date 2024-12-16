package bsuir.chernikov.main.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddressDto {
    private Integer id;
    private String name;
    private String country;
    private String city;
    private String address;
    private String additional;
    private List<Double> coordinates;
}