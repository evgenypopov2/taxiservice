package ru.otus.common.dto;


import java.io.Serializable;

public class OrderRequestDTO implements Serializable {
    private String phone;
    private Double startLat;
    private Double startLon;
    private Double endLat;
    private Double endLon;

    public OrderRequestDTO(String phone, Double startLat, Double startLon, Double endLat, Double endLon) {
        this.phone = phone;
        this.startLat = startLat;
        this.startLon = startLon;
        this.endLat = endLat;
        this.endLon = endLon;
    }

    public OrderRequestDTO() {
    }

    public void setEndLat(Double endLat) {
        this.endLat = endLat;
    }

    public void setStartLon(Double startLon) {
        this.startLon = startLon;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getStartLat() {
        return startLat;
    }

    public void setStartLat(Double startLat) {
        this.startLat = startLat;
    }

    public Double getStartLon() {
        return startLon;
    }

    public Double getEndLat() {
        return endLat;
    }

    public Double getEndLon() {
        return endLon;
    }

    public void setEndLon(Double endLon) {
        this.endLon = endLon;
    }
}
