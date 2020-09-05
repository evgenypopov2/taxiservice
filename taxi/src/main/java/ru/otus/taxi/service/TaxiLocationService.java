package ru.otus.taxi.service;

import lombok.AllArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.common.dto.CarInfoDTO;
import ru.otus.common.dto.TaxiLocationDTO;
import ru.otus.taxi.model.TaxiCar;
import ru.otus.taxi.model.TaxiLocation;
import ru.otus.taxi.model.TaxiState;
import ru.otus.taxi.model.TaxiVendor;
import ru.otus.taxi.repository.TaxiCarRepository;
import ru.otus.taxi.repository.TaxiLocationRepository;
import ru.otus.taxi.repository.TaxiModelRepository;
import ru.otus.taxi.repository.TaxiVendorRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaxiLocationService {

    private final TaxiLocationRepository taxiLocationRepository;
    private final TaxiCarRepository taxiCarRepository;

    @Transactional
    public void fixTaxiLocation(UUID taxiId, double locationLat, double locationLon) {
        taxiCarRepository.findById(taxiId).ifPresent(
                taxiCar -> fixTaxiLocation(taxiCar, locationLat, locationLon));
    }

    @Transactional
    public void fixTaxiLocation(TaxiLocationDTO taxiLocationDTO) {
        Optional.of(taxiCarRepository.findByPhone(taxiLocationDTO.getPhone())).ifPresent(
                taxiCar -> fixTaxiLocation(taxiCar, taxiLocationDTO.getLocationLat(), taxiLocationDTO.getLocationLon())
        );
    }

    private void fixTaxiLocation(TaxiCar taxiCar, double locationLat, double locationLon) {
        Date currentTime = new Date();
        TaxiLocation taxiLocation = new TaxiLocation();
        taxiLocation.setTaxiCar(taxiCar);
        taxiLocation.setLocationLat(locationLat);
        taxiLocation.setLocationLon(locationLon);
        taxiLocation.setTime(currentTime);
        taxiLocationRepository.save(taxiLocation);

        taxiCar.setLastLocationTime(currentTime);
        taxiCar.setLastLocationLat(locationLat);
        taxiCar.setLastLocationLon(locationLon);
        taxiCar.setState(TaxiState.WORKING);
        taxiCarRepository.save(taxiCar);
    }
}
