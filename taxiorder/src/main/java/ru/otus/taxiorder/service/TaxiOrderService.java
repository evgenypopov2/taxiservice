package ru.otus.taxiorder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.common.dto.*;
import ru.otus.taxiorder.model.TaxiOrder;
import ru.otus.taxiorder.repository.TaxiOrderRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaxiOrderService {

    private final TaxiOrderRepository taxiOrderRepository;
    private final TaxiOrderMessageSender taxiOrderMessageSender;

    @Transactional
    public OrderRequestResponseDTO processOrderRequest(OrderRequestDTO orderRequestDTO) {

        ClientDTO client = taxiOrderMessageSender.getClientInfo(orderRequestDTO.getPhone());

        if (client != null) {
            RouteDTO routeDTO = taxiOrderMessageSender.calcRoute(
                    new RouteCalcDTO(orderRequestDTO.getStartLat(), orderRequestDTO.getStartLon(),
                            orderRequestDTO.getEndLat(), orderRequestDTO.getEndLon()));

            TaxiOrder taxiOrder = new TaxiOrder();
            taxiOrder.setClientId(client.getId());
            taxiOrder.setClientPhone(client.getPhone());
            taxiOrder.setStartLat(orderRequestDTO.getStartLat());
            taxiOrder.setStartLon(orderRequestDTO.getStartLon());
            taxiOrder.setEndLat(orderRequestDTO.getEndLat());
            taxiOrder.setEndLon(orderRequestDTO.getEndLon());
            taxiOrder.setRequestDate(new Date());
            taxiOrder.setRoute(routeDTO);
            taxiOrder = taxiOrderRepository.save(taxiOrder);

            OrderRequestResponseDTO responseDTO = new OrderRequestResponseDTO();
            responseDTO.setId(taxiOrder.getId());
            responseDTO.setStartLat(orderRequestDTO.getStartLat());
            responseDTO.setStartLon(orderRequestDTO.getStartLon());
            responseDTO.setEndLat(orderRequestDTO.getEndLat());
            responseDTO.setEndLon(orderRequestDTO.getEndLon());
            responseDTO.setRoute(routeDTO);
            return responseDTO;
        }
        return null;
    }

    public TaxiAroundListDTO processOrderOrder(OrderOrderDTO orderOrderDTO) {

        TaxiAroundListDTO responseDTO = new TaxiAroundListDTO();
        responseDTO.setOrderId(orderOrderDTO.getOrderId());

        TaxiOrder taxiOrder = taxiOrderRepository.findById(orderOrderDTO.getOrderId()).orElse(null);

        if (taxiOrder != null) {
            taxiOrder.setOrderDate(new Date());
            taxiOrder = taxiOrderRepository.save(taxiOrder);

            for (double distance = 1D; distance < 3.5D; distance++) {
                List<TaxiDTO> taxiAroundList = taxiOrderMessageSender.getTaxiAround(
                        new TaxiAroundDTO(taxiOrder.getStartLat(), taxiOrder.getStartLon(), distance));
                if (taxiAroundList != null && taxiAroundList.size() > 0) {
                    responseDTO.setTaxiList(taxiAroundList);
                    responseDTO.setRoute(taxiOrder.getRoute());
                    break;
                }
            }
        }
        return responseDTO;
    }

    public OrderOrderResponseDTO processTakeOrder(TaxiTakeOrderDTO takeOrderDTO) {
        OrderOrderResponseDTO response = new OrderOrderResponseDTO();
        response.setOrderId(takeOrderDTO.getOrderId());
        TaxiOrder taxiOrder = taxiOrderRepository.findById(takeOrderDTO.getOrderId()).orElse(null);

        if (taxiOrder != null && taxiOrder.getTaxiId() == null) {
            TaxiDTO taxi = taxiOrderMessageSender.getTaxiInfo(takeOrderDTO.getPhone());

            if (taxi != null) {
                taxiOrder.setTaxiId(taxi.getId());
                taxiOrder.setTaxiPhone(taxi.getPhone());
                taxiOrder = taxiOrderRepository.save(taxiOrder);

                taxiOrderMessageSender.sendTaxiIsBusy(taxi.getId());

                response.setTaxiId(taxi.getId());
                response.setClientId(taxiOrder.getClientId());
                response.setClientPhone(taxiOrder.getClientPhone());
                response.setCarModel(taxi.getCarModel());
                response.setCarColor(taxi.getCarColor());
                response.setCarNumber(taxi.getCarNumber());
                response.setCarVendor(taxi.getCarVendor());
                response.setDriverName(taxi.getFirstName());
                response.setDriverPhone(taxi.getPhone());
                response.setLocationLat(taxi.getLocationLat());
                response.setLocationLon(taxi.getLocationLon());
                response.setEstimatedTime(5);
            }
        }
        return response;
    }
}
