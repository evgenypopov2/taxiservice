package ru.otus.common.dto;

import java.io.Serializable;

public class TaxiTakeOrderDTO implements Serializable {
    private String phone;
    private long orderId;

    public TaxiTakeOrderDTO(long orderId) {
        this.orderId = orderId;
    }

    public TaxiTakeOrderDTO() {
    }

    public TaxiTakeOrderDTO(String phone, long orderId) {
        this.phone = phone;
        this.orderId = orderId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
