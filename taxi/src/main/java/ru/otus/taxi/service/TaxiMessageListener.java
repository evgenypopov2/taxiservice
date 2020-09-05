package ru.otus.taxi.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.otus.common.dto.*;
import ru.otus.taxi.dto.TaxiDtoCreator;

import java.util.List;
import java.util.UUID;

import static ru.otus.common.config.CommonRabbitConfig.*;
import static ru.otus.taxi.dto.TaxiDtoCreator.createTaxiDTO;

@Slf4j
@Component
@AllArgsConstructor
public class TaxiMessageListener {

    private final TaxiCarService taxiCarService;
    private final TaxiLocationService taxiLocationService;
    private final TaxiVendorService taxiVendorService;

    @RabbitListener(queues = QUEUE_TAXI)
    public TaxiDTO processCreateTaxiMessage(TaxiDTO taxiCreateDTO) {
        log.info("Create taxi message received: {}", taxiCreateDTO);
        return createTaxiDTO(taxiCarService.createTaxiCar(taxiCreateDTO));
    }

    @RabbitListener(queues = QUEUE_TAXI_INFO)
    public TaxiDTO processGetTaxiInfoMessage(String phone) {
        log.info("Get taxi info message received: {}", phone);
        return createTaxiDTO(taxiCarService.findByPhone(phone));
    }

    @RabbitListener(queues = QUEUE_TAXI_START_WORK)
    public void processStartWorkTaxi(TaxiStartWorkDTO taxiStartWorkDTO) {
        log.info("Start work taxi message received: {}", taxiStartWorkDTO);
        taxiCarService.startWork(taxiStartWorkDTO);
    }

    @RabbitListener(queues = QUEUE_TAXI_IS_BUSY)
    public void processTaxiIsBusy(UUID taxiId) {
        log.info("Taxi is busy message received: {}", taxiId);
        taxiCarService.taxiIsBusy(taxiId);
    }

    @RabbitListener(queues = QUEUE_TAXI_LOCATION)
    public void processTaxiLocation(TaxiLocationDTO taxiLocationDTO) {
        log.info("Taxi location message received: {}", taxiLocationDTO);
        taxiLocationService.fixTaxiLocation(taxiLocationDTO);
    }

    @RabbitListener(queues = QUEUE_GET_CAR_INFO)
    public CarInfoDTO processGetCarInfo(String query) {
        log.info("Get car info message received: {}", query);
        return taxiVendorService.getCarInfo();
    }

    @RabbitListener(queues = QUEUE_TAXI_AROUND)
    public List<TaxiDTO> processTaxiAround(TaxiAroundDTO taxiAroundDTO) {
        log.info("Taxi around message received: {}", taxiAroundDTO);
        return TaxiDtoCreator.createTaxiDtoList(
                taxiCarService.findFreeTaxiInSquare(
                        taxiAroundDTO.getLocationLat(),
                        taxiAroundDTO.getLocationLon(),
                        taxiAroundDTO.getDistance()));
    }
}
