package ru.otus.taxi.dto;

import ru.otus.common.dto.CarModelDTO;
import ru.otus.common.dto.CarVendorDTO;
import ru.otus.common.dto.TaxiDTO;
import ru.otus.taxi.model.TaxiCar;
import ru.otus.taxi.model.TaxiModel;
import ru.otus.taxi.model.TaxiVendor;

import java.util.List;
import java.util.stream.Collectors;

public class TaxiDtoCreator {

    public static TaxiDTO createTaxiDTO(TaxiCar taxiCar) {
        TaxiDTO taxiDTO = new TaxiDTO();
        taxiDTO.setId(taxiCar.getId());
        taxiDTO.setFirstName(taxiCar.getFirstName());
        taxiDTO.setLastName(taxiCar.getLastName());
        taxiDTO.setPhone(taxiCar.getPhone());
        taxiDTO.setEmail(taxiCar.getEmail());
        taxiDTO.setCarNumber(taxiCar.getCarNumber());
        taxiDTO.setCarColor(taxiCar.getCarColor().getId());
        taxiDTO.setCarVendor(taxiCar.getModel().getVendor().getName());
        taxiDTO.setCarModel(taxiCar.getModel().getName());
        taxiDTO.setLocationLat(taxiCar.getLastLocationLat());
        taxiDTO.setLocationLon(taxiCar.getLastLocationLon());
        return taxiDTO;
    }

    public static List<TaxiDTO> createTaxiDtoList(List<TaxiCar> taxiCarList) {
        return taxiCarList.stream().map(TaxiDtoCreator::createTaxiDTO).collect(Collectors.toList());
    }

    public static CarVendorDTO createCarVendorDTO(TaxiVendor taxiVendor) {
        CarVendorDTO carVendorDTO = new CarVendorDTO();
        carVendorDTO.setName(taxiVendor.getName());
        return carVendorDTO;
    }

    public static CarModelDTO createTaxiModelDto(TaxiModel taxiModel) {
        return new CarModelDTO(taxiModel.getName(), taxiModel.getType());
    }
    public static List<CarModelDTO> createTaxiModelDtoList(List<TaxiModel> taxiModelList) {
        return taxiModelList.stream().map(TaxiDtoCreator::createTaxiModelDto).collect(Collectors.toList());
    }
}
