package ru.otus.common.dto;

import java.io.Serializable;

public class OrderCancelDTO implements Serializable {
    private String messageClass;
    private Long orderId;
    private String phone;

    public OrderCancelDTO() {
        this.messageClass = this.getClass().getSimpleName();
    }

    public OrderCancelDTO(Long orderId, String phone) {
        this.messageClass = this.getClass().getSimpleName();
        this.orderId = orderId;
        this.phone = phone;
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

    public String getMessageClass() {
        return messageClass;
    }

    public void setMessageClass(String messageClass) {
        this.messageClass = messageClass;
    }
}
