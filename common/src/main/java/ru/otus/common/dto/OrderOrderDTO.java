package ru.otus.common.dto;

import ru.otus.common.model.TaxiType;

import java.io.Serializable;

public class OrderOrderDTO implements Serializable {
    private String phone;
    private Long orderId;
    private TaxiType taxiType;

    public OrderOrderDTO(String phone, Long orderId, TaxiType taxiType) {
        this.phone = phone;
        this.orderId = orderId;
        this.taxiType = taxiType;
    }

    public OrderOrderDTO() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public TaxiType getTaxiType() {
        return taxiType;
    }

    public void setTaxiType(TaxiType taxiType) {
        this.taxiType = taxiType;
    }
}
