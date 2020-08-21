package ru.otus.route.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.otus.route.dto.CalcRouteDTO;

import static ru.otus.route.config.RouteRabbitMqConfiguration.QUEUE_NAME;

@Slf4j
@Component
public class RouteRabbitMqListener {

    @RabbitListener(queues = QUEUE_NAME)
    public void calcRoute(Message message) {
        log.info("message from queue : {}" + message.toString());
    }
}
