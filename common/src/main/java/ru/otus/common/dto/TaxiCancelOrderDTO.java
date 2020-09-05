package ru.otus.common.dto;

import java.io.Serializable;

public class TaxiCancelOrderDTO implements Serializable {
    private long orderId;

    public TaxiCancelOrderDTO(long orderId) {
        this.orderId = orderId;
    }

    public TaxiCancelOrderDTO() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

}
