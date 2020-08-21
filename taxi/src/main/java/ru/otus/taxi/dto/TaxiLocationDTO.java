package ru.otus.taxi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class TaxiLocationDTO {
    private final UUID id;
    private final double lat;
    private final double lon;
}
