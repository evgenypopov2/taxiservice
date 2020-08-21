package ru.otus.route.service;

import org.springframework.stereotype.Service;
import ru.otus.route.model.RoutePart;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService {

    private static final double COORDS_SHIFT_1KM_Y = 0.008951D;
    private static final double COORDS_SHIFT_1KM_X = 0.015879D;
    private static final double AVERAGE_STRAIGHT_PART_LENGTH = 0.2D;
    private static final double MIN_SPEED = 20D;
    private static final double MAX_SPEED = 80D;

    public List<RoutePart> calcRoute(
            final double startLat0, final double startLon0,
            final double endLat0, final double endLon0) {

        List<RoutePart> routeParts = new ArrayList<>();
        routeParts.add(createRoutePart(startLat0, startLon0, 0D));

        int signX = startLon0 < endLon0 ? 1 : -1;
        int signY = startLat0 < endLat0 ? 1 : -1;
        double shiftX = Math.abs(startLon0 - endLon0) / COORDS_SHIFT_1KM_X;
        double shiftY = Math.abs(startLat0 - endLat0) / COORDS_SHIFT_1KM_Y;
        double directDistance = Math.sqrt(shiftX * shiftX + shiftY * shiftY);
        long partCount = Math.round(directDistance / AVERAGE_STRAIGHT_PART_LENGTH);
        double stepX = shiftX / partCount * COORDS_SHIFT_1KM_X;
        double stepY = shiftY / partCount * COORDS_SHIFT_1KM_Y;

        double lat = startLat0;
        double lon = startLon0;

        for (long i = 0; i < partCount; i++) {
            lat += signY * randomDouble(stepY * 0.8, stepY * 1.2);
            lon += signX * randomDouble(stepX * 0.8, stepX * 1.2);
            routeParts.add(createRoutePart(lat, lon, randomSpeed()));
            signX = lon < endLon0 ? 1 : -1;
            signY = lat < endLat0 ? 1 : -1;
        }
        routeParts.add(createRoutePart(endLat0, endLon0, randomSpeed()));
        return routeParts;
    }

    private RoutePart createRoutePart(double lat, double lon, double speed) {
        return new RoutePart(lat, lon, speed);
    }

    private double randomSpeed() {
        return randomDouble(MIN_SPEED, MAX_SPEED);
    }

    private double randomDouble(double min, double max) {
        if (max < min) {
            double x = max;
            max = min;
            min = x;
        }
        return Math.random() * (max - min) + min;
    }
}
