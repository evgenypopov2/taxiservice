package ru.otus.common.dto;

public class TaxiAroundDTO extends TaxiLocationDTO {
    private Double distance;

    public TaxiAroundDTO(Double locationLat, Double locationLon, Double distance) {
        super(locationLat, locationLon);
        this.distance = distance;
    }

    public TaxiAroundDTO() {
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
