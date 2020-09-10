package ru.otus.common.dto;

import java.io.Serializable;

public class ClientOrderForTaxiDTO implements Serializable {
    private long orderId;
    private String clientPhone;
    private RouteDTO route;

    public ClientOrderForTaxiDTO(long orderId, RouteDTO route) {
        this.orderId = orderId;
        this.route = route;
    }

    public ClientOrderForTaxiDTO() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
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
}
