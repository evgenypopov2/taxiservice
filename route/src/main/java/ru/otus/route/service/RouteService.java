package ru.otus.route.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.common.dto.RouteCalcDTO;
import ru.otus.common.dto.RouteDTO;
import ru.otus.common.dto.RoutePartDTO;
import ru.otus.common.dto.RoutePriceDTO;
import ru.otus.common.model.TaxiType;
import ru.otus.route.model.RoutePart;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.otus.common.model.GeoConstants.COORDS_SHIFT_1KM_X;
import static ru.otus.common.model.GeoConstants.COORDS_SHIFT_1KM_Y;

@Service
@AllArgsConstructor
public class RouteService {

    private static final double AVERAGE_STRAIGHT_PART_LENGTH = 0.2D;
    private static final double MIN_SPEED = 20D;
    private static final double MAX_SPEED = 80D;

    private final PriceService priceService;

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
            lat += signY * randomDouble(stepY * 0.5, stepY * 1.5);
            lon += signX * randomDouble(stepX * 0.5, stepX * 1.5);
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

    public RouteDTO calcRoute(RouteCalcDTO routeCalcDTO) {
        List<RoutePart> routePartList = calcRoute(
                routeCalcDTO.getStartLat(), routeCalcDTO.getStartLon(),
                routeCalcDTO.getEndLat(), routeCalcDTO.getEndLon()
        );
        if (routePartList.size() > 0) {
            double length = calcRouteLength(routePartList);
            RouteDTO routeDTO = new RouteDTO();
            routeDTO.setLength((double) length);
            routeDTO.setRouteParts(
                    routePartList.stream()
                            .map(routePart -> new RoutePartDTO(routePart.getLat(), routePart.getLon(), routePart.getSpeed()))
                            .collect(Collectors.toList())
            );
            routeDTO.setPrices(Stream.of(TaxiType.values())
                    .map(type -> new RoutePriceDTO(type, Math.round(priceService.getPriceOf1kmByTaxiType(type) * length)))
                    .collect(Collectors.toList()));
            return routeDTO;
        }
        return null;
    }

    private double calcRouteLength(List<RoutePart> routePartList) {
        double length = 0;
        double lastLat = 0D;
        double lastLon = 0D;

        for (int i = 0; i < routePartList.size(); i++) {
            RoutePart routePart = routePartList.get(i);
            if (i > 0) {
                length += Math.sqrt(square((routePart.getLat() - lastLat)/COORDS_SHIFT_1KM_Y) +
                        square((routePart.getLon() - lastLon)/COORDS_SHIFT_1KM_X));
            }
            lastLat = routePart.getLat();
            lastLon = routePart.getLon();
        }
        return length;
    }

    private double square(double x) {
        return x * x;
    }
}
