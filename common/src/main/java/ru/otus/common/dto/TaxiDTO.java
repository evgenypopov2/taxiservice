package ru.otus.common.dto;


public class TaxiDTO extends ClientDTO {
    private String carVendor;
    private String carModel;
    private String carNumber;
    private String carColor;

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

    public String getCarVendor() {
        return carVendor;
    }

    public void setCarVendor(String carVendor) {
        this.carVendor = carVendor;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarModel() {
        return carModel;
    }
}
