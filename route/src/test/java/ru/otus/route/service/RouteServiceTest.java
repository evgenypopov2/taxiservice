package ru.otus.route.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.route.model.RoutePart;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class RouteServiceTest {

    private RouteService routeService;

    @BeforeEach
    void init() {
        routeService = new RouteService();
    }
    @Test
    void calcRouteTest() {
        List<RoutePart> routeParts = routeService.calcRoute(55.645978, 37.495940, 55.701670, 37.688379);
        log.info(routeParts.toString());
    }
}