package ru.otus.common.dto;

import ru.otus.common.model.TaxiType;

import java.io.Serializable;

public class OrderOrderDTO implements Serializable {
    private String phone;
    private long orderId;
    private TaxiType taxiType;

    public OrderOrderDTO(String phone, long orderId, TaxiType taxiType) {
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

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public TaxiType getTaxiType() {
        return taxiType;
    }

    public void setTaxiType(TaxiType taxiType) {
        this.taxiType = taxiType;
    }
}
