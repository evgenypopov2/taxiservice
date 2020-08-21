package ru.otus.route.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CalcRouteDTO {
    private Double startLat;
    private Double startLon;
    private Double endLat;
    private Double endLon;
}
