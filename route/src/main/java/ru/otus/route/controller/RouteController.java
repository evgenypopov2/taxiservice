package ru.otus.route.controller;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.route.dto.CalcRouteDTO;
import ru.otus.route.model.RoutePart;
import ru.otus.route.service.RouteService;

import java.util.List;

import static ru.otus.route.config.RouteRabbitMqConfiguration.QUEUE_NAME;

@RestController
@RequestMapping("/route")
@AllArgsConstructor
public class RouteController {

    private final RouteService routeService;
    private final AmqpTemplate template;

    @PostMapping("/calc")
    private ResponseEntity<List<RoutePart>> calcRoute(@RequestBody CalcRouteDTO calcRouteDTO) {
        template.convertAndSend(QUEUE_NAME, calcRouteDTO.toString());
        return ResponseEntity.ok(routeService.calcRoute(
                calcRouteDTO.getStartLat(), calcRouteDTO.getStartLon(),
                calcRouteDTO.getEndLat(), calcRouteDTO.getEndLon()));
    }
}
