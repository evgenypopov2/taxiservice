package ru.otus.common.dto;

import java.io.Serializable;
import java.util.UUID;

public class OrderOrderResponseDTO implements Serializable {
    private String messageClass;
    private Long orderId;
    private UUID clientId;
    private String clientPhone;
    private UUID taxiId;
    private String driverName;
    private String driverPhone;
    private String carNumber;
    private String carType;
    private String carColor;
    private String carVendor;
    private String carModel;
    private Long estimatedTime;
    private Double locationLat;
    private Double locationLon;

    public OrderOrderResponseDTO() {
        this.messageClass = this.getClass().getSimpleName();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarVendor() {
        return carVendor;
    }

    public void setCarVendor(String carVendor) {
        this.carVendor = carVendor;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Long getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Long estimatedTime) {
        this.estimatedTime = estimatedTime;
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

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public UUID getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(UUID taxiId) {
        this.taxiId = taxiId;
    }
    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getMessageClass() {
        return messageClass;
    }

    public void setMessageClass(String messageClass) {
        this.messageClass = messageClass;
    }
}
