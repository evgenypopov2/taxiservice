package ru.otus.apigateway.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.otus.common.dto.ClientRegistrationDTO;
import ru.otus.common.dto.TaxiRegistrationDTO;
import ru.otus.common.dto.*;

import static ru.otus.apigateway.dto.ApiGatewayDtoCreator.*;
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

    public void sendTaxiLocation(TaxiLocationDTO taxiLocationDTO) {
        rabbitTemplate.convertAndSend(QUEUE_TAXI_LOCATION, taxiLocationDTO);
    }

    public OrderRequestResponseDTO sendOrderRequest(OrderRequestDTO orderRequestDTO) {
        return (OrderRequestResponseDTO) rabbitTemplate.convertSendAndReceive(
                QUEUE_ORDER_REQUEST, orderRequestDTO);
    }

    public TaxiAroundListDTO sendOrderOrder(OrderOrderDTO orderOrderDTO) {
        return (TaxiAroundListDTO) rabbitTemplate.convertSendAndReceive(
                QUEUE_ORDER_ORDER, orderOrderDTO);
    }

    public OrderOrderResponseDTO sendTakeOrder(TaxiTakeOrderDTO takeOrderDTO) {
        return (OrderOrderResponseDTO) rabbitTemplate.convertSendAndReceive(
                QUEUE_TAXI_TAKE_ORDER, takeOrderDTO);
    }

    public CarInfoDTO sendGetCarInfo() {
        return (CarInfoDTO) rabbitTemplate.convertSendAndReceive(
                QUEUE_GET_CAR_INFO, "Give me please car info");
    }
}
