package ru.otus.apigateway.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.otus.apigateway.dto.ClientRegistrationDTO;
import ru.otus.apigateway.dto.TaxiRegistrationDTO;
import ru.otus.common.dto.ClientDTO;
import ru.otus.common.dto.TaxiDTO;

import static ru.otus.apigateway.dto.DtoCreator.*;
import static ru.otus.common.config.CommonRabbitConfig.*;

@Slf4j
@Service
@AllArgsConstructor
public class RabbitMessageSender {

    private final RabbitTemplate rabbitTemplate;

    public ClientDTO sendCreateClient(ClientRegistrationDTO clientRegistrationDTO) {
        return (ClientDTO) rabbitTemplate.convertSendAndReceive(
                QUEUE_CLIENTS, createClientDTO(clientRegistrationDTO));
    }

    public TaxiDTO sendCreateTaxi(TaxiRegistrationDTO taxiRegistrationDTO) {
        return (TaxiDTO) rabbitTemplate.convertSendAndReceive(
                QUEUE_TAXI, createTaxiDTO(taxiRegistrationDTO));
    }

    public void sendTaxiStartWork(String phone) {
        rabbitTemplate.convertAndSend(QUEUE_TAXI_START_WORK, createTaxiStartWorkDTO(phone));
    }

    public void sendTaxiLocation(String phone, double lat, double lon) {
        rabbitTemplate.convertAndSend(QUEUE_TAXI_LOCATION, createTaxiLocationDTO(phone, lat, lon));
    }
}
