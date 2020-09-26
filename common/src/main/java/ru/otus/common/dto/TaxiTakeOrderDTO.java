package ru.otus.common.dto;

import java.io.Serializable;

public class TaxiTakeOrderDTO implements Serializable {
    private String phone;
    private Long orderId;

    public TaxiTakeOrderDTO(Long orderId) {
        this.orderId = orderId;
    }

    public TaxiTakeOrderDTO() {
    }

    public TaxiTakeOrderDTO(String phone, Long orderId) {
        this.phone = phone;
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
