package ru.otus.route.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.otus.common.dto.RouteCalcDTO;
import ru.otus.common.dto.RouteDTO;
import ru.otus.route.service.RouteService;

import static ru.otus.common.config.CommonRabbitConfig.QUEUE_ROUTE_CALC;

@Slf4j
@Component
@AllArgsConstructor
public class RouteMessageListener {

    private final RouteService routeService;

    @RabbitListener(queues = QUEUE_ROUTE_CALC)
    public RouteDTO calcRoute(RouteCalcDTO routeCalcDTO) {
        log.info("Calc route requested");
        return routeService.calcRoute(routeCalcDTO);
    }
}
