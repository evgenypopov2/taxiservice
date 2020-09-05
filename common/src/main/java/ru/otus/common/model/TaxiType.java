package ru.otus.common.model;

public enum TaxiType {
    STANDARD("Стандарт"),
    COMFORT("Комфорт"),
    BUSINESS("Бизнес"),
    PREMIUM("Премиум");

    private final String id;

    TaxiType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    public static TaxiType fromId(String id) {
        for (TaxiType at : TaxiType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}
