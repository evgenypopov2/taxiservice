package ru.otus.route.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class RoutePart implements Serializable {
    private final Double lat;
    private final Double lon;
    private final Double speed;
}
