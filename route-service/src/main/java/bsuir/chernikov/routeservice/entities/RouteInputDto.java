package bsuir.chernikov.routeservice.entities;

import lombok.Data;

@Data
public class RouteInputDto {
    private Double startAddress;
    private Double endAddress;

    private Double weight;
}