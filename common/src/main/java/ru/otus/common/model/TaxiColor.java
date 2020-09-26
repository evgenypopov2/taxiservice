package ru.otus.common.model;

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

    private final String id;

    TaxiColor(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    public static TaxiColor fromId(String id) {
        for (TaxiColor at : TaxiColor.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}
