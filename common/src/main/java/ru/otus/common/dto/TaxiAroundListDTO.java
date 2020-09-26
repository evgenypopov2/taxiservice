package ru.otus.common.dto;

import java.io.Serializable;
import java.util.List;

public class TaxiAroundListDTO implements Serializable {
    private Long orderId;
    private List<TaxiDTO> taxiList;
    private RouteDTO route;

    public TaxiAroundListDTO(Long orderId, List<TaxiDTO> taxiList, RouteDTO route) {
        this.orderId = orderId;
        this.taxiList = taxiList;
        this.route = route;
    }

    public TaxiAroundListDTO() {
    }

    public List<TaxiDTO> getTaxiList() {
        return taxiList;
    }

    public void setTaxiList(List<TaxiDTO> taxiList) {
        this.taxiList = taxiList;
    }

    public RouteDTO getRoute() {
        return route;
    }

    public void setRoute(RouteDTO route) {
        this.route = route;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
