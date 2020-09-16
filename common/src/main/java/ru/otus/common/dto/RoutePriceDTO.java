package ru.otus.common.dto;

import ru.otus.common.model.TaxiType;

import java.io.Serializable;

public class RoutePriceDTO implements Serializable {
    private TaxiType taxiType;
    private Long price;

    public RoutePriceDTO(TaxiType taxiType, Long price) {
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
