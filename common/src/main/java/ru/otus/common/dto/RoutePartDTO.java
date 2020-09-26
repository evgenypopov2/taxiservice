package ru.otus.common.dto;

import java.io.Serializable;

public class RoutePartDTO implements Serializable {
    private Double lat;
    private Double lon;
    private Double speed;

    public RoutePartDTO(Double lat, Double lon, Double speed) {
        this.lat = lat;
        this.lon = lon;
        this.speed = speed;
    }

    public RoutePartDTO() {
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }
}
