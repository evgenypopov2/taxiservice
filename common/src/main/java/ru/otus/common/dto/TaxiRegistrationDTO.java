package ru.otus.common.dto;

public class TaxiRegistrationDTO extends ClientRegistrationDTO {
    private String carVendor;
    private String carModel;
    private String carNumber;
    private String carColor;

    public TaxiRegistrationDTO() {
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

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }
}
