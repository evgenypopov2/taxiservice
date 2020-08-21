package ru.otus.taxi.model;

import org.springframework.lang.Nullable;

public enum TaxiType {
    STANDARD("Стандарт"),
    COMFORT("Комфорт"),
    BUSINESS("Бизнес"),
    PREMIUM("Премиум");

    private String id;

    TaxiType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static TaxiType fromId(String id) {
        for (TaxiType at : TaxiType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }

    }
