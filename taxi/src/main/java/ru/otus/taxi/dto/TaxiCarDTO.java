package ru.otus.taxi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaxiCarDTO {
    private final String driverName;
    private final String driverPhone;
    private final String number;
    private final String color;
    private final String taxiVendor;
    private final String taxiModel;
}
