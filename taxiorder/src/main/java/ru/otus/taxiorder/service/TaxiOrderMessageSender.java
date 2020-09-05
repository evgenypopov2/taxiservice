package ru.otus.taxiorder.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.otus.common.dto.*;

import java.util.List;
import java.util.UUID;

import static ru.otus.common.config.CommonRabbitConfig.*;

@Slf4j
@Service
@AllArgsConstructor
public class TaxiOrderMessageSender {

    private final RabbitTemplate rabbitTemplate;

    public ClientDTO getClientInfo(String phone) {
        return (ClientDTO) rabbitTemplate.convertSendAndReceive(QUEUE_CLIENTS_INFO, phone);
    }

    public TaxiDTO getTaxiInfo(String phone) {
        return (TaxiDTO) rabbitTemplate.convertSendAndReceive(QUEUE_TAXI_INFO, phone);
    }

    public RouteDTO calcRoute(RouteCalcDTO routeCalcDTO) {
        return (RouteDTO) rabbitTemplate.convertSendAndReceive(QUEUE_ROUTE_CALC, routeCalcDTO);
    }

    public List<TaxiDTO> getTaxiAround(TaxiAroundDTO taxiAroundDTO) {
        return (List<TaxiDTO>) rabbitTemplate.convertSendAndReceive(QUEUE_TAXI_AROUND, taxiAroundDTO);
    }

    public void sendTaxiIsBusy(UUID id) {
        rabbitTemplate.convertAndSend(QUEUE_TAXI_IS_BUSY, id);
    }
}
