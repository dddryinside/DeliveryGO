package bsuir.chernikov.main.dto;

import lombok.Data;

@Data
public class OrderDto {
    private Integer id;
    private String name;
    private String category;

    private String startPoint;
    private Integer startPointId;
    private String endPoint;
    private Integer endPointId;

    private Double distance;
    private Double weight;
    private Double calculatedTime;
    private String stringTime;
    private Double co2Emission;

    private String status;
    private String createdAt;
    private String courier;
    private Integer price;
}
