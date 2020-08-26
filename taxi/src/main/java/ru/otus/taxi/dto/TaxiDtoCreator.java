package ru.otus.taxi.dto;

import ru.otus.common.dto.TaxiDTO;
import ru.otus.taxi.model.TaxiCar;

import java.util.List;
import java.util.stream.Collectors;

public class TaxiDtoCreator {

    public static TaxiDTO createTaxiDTO(TaxiCar taxiCar) {
        TaxiDTO taxiCreateDTO = new TaxiDTO();
        taxiCreateDTO.setId(taxiCar.getId());
        taxiCreateDTO.setFirstName(taxiCar.getFirstName());
        taxiCreateDTO.setLastName(taxiCar.getLastName());
        taxiCreateDTO.setPhone(taxiCar.getPhone());
        taxiCreateDTO.setEmail(taxiCar.getEmail());
        taxiCreateDTO.setCarNumber(taxiCar.getCarNumber());
        taxiCreateDTO.setCarColor(taxiCar.getCarColor().getId());
        taxiCreateDTO.setCarVendor(taxiCar.getModel().getVendor().getName());
        taxiCreateDTO.setCarModel(taxiCar.getModel().getName());
        return taxiCreateDTO;
    }

    public static List<TaxiDTO> createTaxiDtoList(List<TaxiCar> taxiCarList) {
        return taxiCarList.stream().map(TaxiDtoCreator::createTaxiDTO).collect(Collectors.toList());
    }
}
