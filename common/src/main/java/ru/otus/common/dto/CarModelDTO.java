package ru.otus.common.dto;

import ru.otus.common.model.TaxiType;

import java.io.Serializable;

public class CarModelDTO implements Serializable {
    private String name;
    private TaxiType taxiType;

    public CarModelDTO(String name, TaxiType taxiType) {
        this.name = name;
        this.taxiType = taxiType;
    }

    public CarModelDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaxiType getTaxiType() {
        return taxiType;
    }

    public void setTaxiType(TaxiType taxiType) {
        this.taxiType = taxiType;
    }
}
