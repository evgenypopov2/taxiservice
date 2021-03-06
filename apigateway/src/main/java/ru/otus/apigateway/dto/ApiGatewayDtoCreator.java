package ru.otus.apigateway.dto;

import ru.otus.common.dto.*;

public class ApiGatewayDtoCreator {
    public static ClientDTO createClientDTO(ClientRegistrationDTO clientRegistrationDTO) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName(clientRegistrationDTO.getFirstName());
        clientDTO.setLastName(clientRegistrationDTO.getLastName());
        clientDTO.setPhone(clientRegistrationDTO.getPhone());
        clientDTO.setEmail(clientRegistrationDTO.getEmail());
        return clientDTO;
    }
    public static TaxiDTO createTaxiDTO(TaxiRegistrationDTO taxiRegistrationDTO) {
        TaxiDTO taxiDTO = new TaxiDTO();
        taxiDTO.setFirstName(taxiRegistrationDTO.getFirstName());
        taxiDTO.setLastName(taxiRegistrationDTO.getLastName());
        taxiDTO.setPhone(taxiRegistrationDTO.getPhone());
        taxiDTO.setEmail(taxiRegistrationDTO.getEmail());
        taxiDTO.setCarNumber(taxiRegistrationDTO.getCarNumber());
        taxiDTO.setCarColor(taxiRegistrationDTO.getCarColor());
        taxiDTO.setCarVendor(taxiRegistrationDTO.getCarVendor());
        taxiDTO.setCarModel(taxiRegistrationDTO.getCarModel());
        return taxiDTO;
    }

    public static TaxiStartWorkDTO createTaxiStartWorkDTO(String phone) {
        TaxiStartWorkDTO taxiStartWorkDTO = new TaxiStartWorkDTO();
        taxiStartWorkDTO.setPhone(phone);
        return taxiStartWorkDTO;
    }
    public static TaxiLocationDTO createTaxiLocationDTO(String phone, double lat, double lon) {
        TaxiLocationDTO taxiLocationDTO = new TaxiLocationDTO();
        taxiLocationDTO.setPhone(phone);
        taxiLocationDTO.setLocationLat(lat);
        taxiLocationDTO.setLocationLon(lon);
        return taxiLocationDTO;
    }

    public static OrderRequestDTO createOrderRequestDTO(String phone, double startLat, double startLon, double endLat, double endLon) {
        return new OrderRequestDTO(phone, startLat, startLon, endLat, endLon);
    }
}
