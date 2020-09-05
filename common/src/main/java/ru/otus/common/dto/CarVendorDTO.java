package ru.otus.common.dto;

import java.io.Serializable;
import java.util.List;

public class CarVendorDTO implements Serializable {
    private String name;
    private List<CarModelDTO> carModelList;

    public CarVendorDTO() {
    }

    public CarVendorDTO(String name, List<CarModelDTO> carModelList) {
        this.name = name;
        this.carModelList = carModelList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CarModelDTO> getCarModelList() {
        return carModelList;
    }

    public void setCarModelList(List<CarModelDTO> carModelList) {
        this.carModelList = carModelList;
    }
}
