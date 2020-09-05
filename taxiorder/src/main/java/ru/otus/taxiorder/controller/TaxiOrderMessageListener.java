package ru.otus.taxiorder.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.otus.common.dto.*;
import ru.otus.taxiorder.service.TaxiOrderService;

import static ru.otus.common.config.CommonRabbitConfig.*;

@Slf4j
@Component
@AllArgsConstructor
public class TaxiOrderMessageListener {

    private final TaxiOrderService taxiOrderService;

    @RabbitListener(queues = QUEUE_ORDER_REQUEST)
    public OrderRequestResponseDTO processOrderRequest(OrderRequestDTO orderRequestDTO) {
        log.info("Order request message received: {}", orderRequestDTO);
        return taxiOrderService.processOrderRequest(orderRequestDTO);
    }

    @RabbitListener(queues = QUEUE_ORDER_ORDER)
    public TaxiAroundListDTO processOrderOrder(OrderOrderDTO orderOrderDTO) {
        log.info("Order order message received: {}", orderOrderDTO);
        return taxiOrderService.processOrderOrder(orderOrderDTO);
    }

    @RabbitListener(queues = QUEUE_TAXI_TAKE_ORDER)
    public OrderOrderResponseDTO processTakeOrder(TaxiTakeOrderDTO takeOrderDTO) {
        log.info("Taxi take order message received: {}", takeOrderDTO);
        return taxiOrderService.processTakeOrder(takeOrderDTO);
    }
}
