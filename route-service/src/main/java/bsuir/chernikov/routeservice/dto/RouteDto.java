package bsuir.chernikov.routeservice.dto;

import lombok.Data;

@Data
public class RouteDto {
    private Integer startPointId;
    private Integer endPointId;

    private Double distance;
}