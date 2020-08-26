package ru.otus.common.dto;

public class TaxiLocationDTO extends TaxiStartWorkDTO {
    private double locationLat;
    private double locationLon;

    public double getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(double locationLat) {
        this.locationLat = locationLat;
    }

    public double getLocationLon() {
        return locationLon;
    }

    public void setLocationLon(double locationLon) {
        this.locationLon = locationLon;
    }
}
