package ru.otus.common.dto;

public class TaxiLocationDTO extends TaxiStartWorkDTO {
    private String messageClass;
    private Double locationLat;
    private Double locationLon;

    public TaxiLocationDTO(Double locationLat, Double locationLon) {
        this.messageClass = this.getClass().getSimpleName();
        this.locationLat = locationLat;
        this.locationLon = locationLon;
    }

    public TaxiLocationDTO() {
        this.messageClass = this.getClass().getSimpleName();
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

    public String getMessageClass() {
        return messageClass;
    }

    public void setMessageClass(String messageClass) {
        this.messageClass = messageClass;
    }
}
