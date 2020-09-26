package ru.otus.common.dto;

import java.io.Serializable;

public class ClientOrderForTaxiDTO implements Serializable {
    private String messageClass;
    private Long orderId;
    private String clientPhone;
    private RouteDTO route;

    public ClientOrderForTaxiDTO(Long orderId, RouteDTO route) {
        this.messageClass = this.getClass().getSimpleName();
        this.orderId = orderId;
        this.route = route;
    }

    public ClientOrderForTaxiDTO() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public RouteDTO getRoute() {
        return route;
    }

    public void setRoute(RouteDTO route) {
        this.route = route;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getMessageClass() {
        return messageClass;
    }

    public void setMessageClass(String messageClass) {
        this.messageClass = messageClass;
    }
}
