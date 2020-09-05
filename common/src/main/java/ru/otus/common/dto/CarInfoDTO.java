package ru.otus.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarInfoDTO implements Serializable {
    private List<CarVendorDTO> carVendorList = new ArrayList<>();

    public CarInfoDTO() {
    }

    public CarInfoDTO(List<CarVendorDTO> carVendorList) {
        this.carVendorList = carVendorList;
    }

    public List<CarVendorDTO> getCarVendorList() {
        return carVendorList;
    }

    public CarInfoDTO setCarVendorList(List<CarVendorDTO> carVendorList) {
        this.carVendorList = carVendorList;
        return this;
    }
}
