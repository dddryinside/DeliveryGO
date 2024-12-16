package bsuir.chernikov.main.dto;

import lombok.Data;

@Data
public class CourierDto {
    private Integer id;
    private String name;
    private String username;
    private String registrationDate;
    private Integer ordersCompleted;
    private Double rating;
    private String phone;
    private String email;
    private String location;
    private String about;
}
