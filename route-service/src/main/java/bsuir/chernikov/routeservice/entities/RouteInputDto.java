package bsuir.chernikov.routeservice.entities;

import lombok.Data;

@Data
public class RouteInputDto {
    private Double startPointX;
    private Double startPointY;

    private Double endPointX;
    private Double endPointY;

    private Double weight;
}
