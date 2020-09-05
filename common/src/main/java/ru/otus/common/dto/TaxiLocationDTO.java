package ru.otus.common.dto;

public class TaxiLocationDTO extends TaxiStartWorkDTO {
    private Double locationLat;
    private Double locationLon;

    public TaxiLocationDTO(Double locationLat, Double locationLon) {
        this.locationLat = locationLat;
        this.locationLon = locationLon;
    }

    public TaxiLocationDTO() {
    }

    public Double getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(Double locationLat) {
        this.locationLat = locationLat;
    }

    public Double getLocationLon() {
        return locationLon;
    }

    public void setLocationLon(Double locationLon) {
        this.locationLon = locationLon;
    }
}
