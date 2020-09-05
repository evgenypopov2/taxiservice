package ru.otus.common.dto;

import ru.otus.common.model.TaxiType;

import java.io.Serializable;

public class RoutePriceDTO implements Serializable {
    private TaxiType taxiType;
    private int price;

    public RoutePriceDTO(TaxiType taxiType, int price) {
        this.taxiType = taxiType;
        this.price = price;
    }

    public RoutePriceDTO() {
    }

    public TaxiType getTaxiType() {
        return taxiType;
    }

    public void setTaxiType(TaxiType taxiType) {
        this.taxiType = taxiType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
