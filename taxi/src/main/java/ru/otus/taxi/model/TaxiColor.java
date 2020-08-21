package ru.otus.taxi.model;

import org.springframework.lang.Nullable;

public enum TaxiColor {
    RED("Красный"),
    ORANGE("Оранжевый"),
    YELLOW("Жёлтый"),
    GREEN("Зелёный"),
    BLUE("Голубой"),
    DARKBLUE("Синий"),
    VIOLET("Фиолетовый"),
    BLACK("Чёрный"),
    GREY("Серый"),
    WHITE("Белый"),
    BROWN("Коричневый");

    private String id;

    TaxiColor(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static TaxiColor fromId(String id) {
        for (TaxiColor at : TaxiColor.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}
