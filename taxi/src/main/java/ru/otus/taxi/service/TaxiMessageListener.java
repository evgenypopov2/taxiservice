package ru.otus.taxi.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.otus.common.dto.TaxiDTO;
import ru.otus.common.dto.TaxiLocationDTO;
import ru.otus.common.dto.TaxiStartWorkDTO;

import static ru.otus.common.config.CommonRabbitConfig.*;
import static ru.otus.taxi.dto.TaxiDtoCreator.createTaxiDTO;

@Slf4j
@Component
@AllArgsConstructor
public class TaxiMessageListener {

    private final TaxiCarService taxiCarService;
    private final TaxiLocationService taxiLocationService;

    @RabbitListener(queues = QUEUE_TAXI)
    public TaxiDTO processCreateTaxiMessage(TaxiDTO taxiCreateDTO) {
        log.info("Create taxi message received: {}", taxiCreateDTO);
        return createTaxiDTO(taxiCarService.createTaxiCar(taxiCreateDTO));
    }

    @RabbitListener(queues = QUEUE_TAXI_START_WORK)
    public void processStartWorkTaxi(TaxiStartWorkDTO taxiStartWorkDTO) {
        log.info("Start work taxi message received: {}", taxiStartWorkDTO);
        taxiCarService.startWork(taxiStartWorkDTO);
    }

    @RabbitListener(queues = QUEUE_TAXI_LOCATION)
    public void processTaxiLocation(TaxiLocationDTO taxiLocationDTO) {
        log.info("Taxi location message received: {}", taxiLocationDTO);
        taxiLocationService.fixTaxiLocation(taxiLocationDTO);
    }
}
