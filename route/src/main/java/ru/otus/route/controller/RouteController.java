package ru.otus.route.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.route.dto.CalcRouteDTO;
import ru.otus.route.model.RoutePart;
import ru.otus.route.service.RouteService;

import java.util.List;

@RestController
@RequestMapping("/route")
@AllArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PostMapping("/calc")
    private ResponseEntity<List<RoutePart>> calcRoute(@RequestBody CalcRouteDTO calcRouteDTO) {
        return ResponseEntity.ok(routeService.calcRoute(
                calcRouteDTO.getStartLat(), calcRouteDTO.getStartLon(),
                calcRouteDTO.getEndLat(), calcRouteDTO.getEndLon()));
    }
}
