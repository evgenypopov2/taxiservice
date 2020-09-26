package ru.otus.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RouteDTO implements Serializable {
    private Double length;
    List<RoutePartDTO> routeParts = new ArrayList<>();
    List<RoutePriceDTO> prices = new ArrayList<>();

    public RouteDTO() {
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public List<RoutePartDTO> getRouteParts() {
        return routeParts;
    }

    public void setRouteParts(List<RoutePartDTO> routeParts) {
        this.routeParts = routeParts;
    }

    public List<RoutePriceDTO> getPrices() {
        return prices;
    }

    public void setPrices(List<RoutePriceDTO> prices) {
        this.prices = prices;
    }
}
