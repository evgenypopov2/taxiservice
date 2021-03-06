package ru.otus.taxi.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.common.dto.TaxiDTO;
import ru.otus.common.dto.TaxiStartWorkDTO;
import ru.otus.common.dto.TaxiStatusDTO;
import ru.otus.taxi.model.TaxiCar;
import ru.otus.common.model.TaxiColor;
import ru.otus.taxi.model.TaxiState;
import ru.otus.common.model.TaxiStatus;
import ru.otus.taxi.repository.TaxiCarRepository;

import java.util.*;

import static ru.otus.common.model.GeoConstants.COORDS_SHIFT_1KM_X;
import static ru.otus.common.model.GeoConstants.COORDS_SHIFT_1KM_Y;

@Service
@AllArgsConstructor
public class TaxiCarService {

    private static final int INACTIVITY_PERIOD = 1; // 1 hour of inactivity

    private final TaxiCarRepository taxiCarRepository;
    private final TaxiModelService taxiModelService;

    public List<TaxiCar> findAll() {
        return taxiCarRepository.findAll();
    }

    public Optional<TaxiCar> findById(UUID id) {
        return taxiCarRepository.findById(id);
    }

    public TaxiCar findByPhone(String phone) {
        return taxiCarRepository.findByPhone(phone);
    }

    public void taxiSetStatus(TaxiStatusDTO taxiStatusDTO) {
        (taxiStatusDTO.getId() == null
                ? Optional.of(taxiCarRepository.findByPhone(taxiStatusDTO.getPhone()))
                : taxiCarRepository.findById(taxiStatusDTO.getId()))
        .map(taxiCar -> {
            taxiCar.setStatus(taxiStatusDTO.getStatus());
            return taxiCarRepository.save(taxiCar);
        });
    }

    public List<TaxiCar> findFreeTaxiInSquare(double locationLat, double locationLon, double distance) {
        double coordsShiftX = COORDS_SHIFT_1KM_X * distance;
        double coordsShiftY = COORDS_SHIFT_1KM_Y * distance;
        return taxiCarRepository.findFreeTaxiInSquare(
                locationLat + coordsShiftY,
                locationLon - coordsShiftX,
                locationLat - coordsShiftY,
                locationLon + coordsShiftX
        );
    }

    public TaxiCar createTaxiCar(TaxiDTO taxiCreateDTO) {
        return taxiCarRepository.save(fillTaxiCar(new TaxiCar(), taxiCreateDTO));
    }

    public TaxiCar updateTaxiCar(UUID id, TaxiDTO taxiCarDTO) {
        return taxiCarRepository.findById(id)
                .map(taxiCar -> taxiCarRepository.save(fillTaxiCar(taxiCar, taxiCarDTO)))
                .orElse(null);
    }

    private TaxiCar fillTaxiCar(TaxiCar taxiCar, TaxiDTO taxiCarDTO) {
        if (taxiCarDTO.getFirstName() != null) {
            taxiCar.setFirstName(taxiCarDTO.getFirstName());
        }
        if (taxiCarDTO.getLastName() != null) {
            taxiCar.setLastName(taxiCarDTO.getLastName());
        }
        if (taxiCarDTO.getPhone() != null) {
            taxiCar.setPhone(taxiCarDTO.getPhone());
        }
        if (taxiCarDTO.getEmail() != null) {
            taxiCar.setEmail(taxiCarDTO.getEmail());
        }
        if (taxiCarDTO.getCarNumber() != null) {
            taxiCar.setCarNumber(taxiCarDTO.getCarNumber());
        }
        if (taxiCarDTO.getCarColor() != null) {
            taxiCar.setCarColor(TaxiColor.fromId(taxiCarDTO.getCarColor()));
        }
        if (taxiCarDTO.getCarModel() != null) {
            taxiCar.setModel(taxiModelService.findByName(taxiCarDTO.getCarModel())
                    .orElse(taxiModelService.findByName("Rio").orElse(null)));
        }
        return taxiCar;
    }

    @Scheduled(fixedDelayString = "${check.inactivity.interval}")    // every 5 minutes
    @Transactional
    public void setNotWorkingTaxiStatus() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, -INACTIVITY_PERIOD);
        taxiCarRepository.setNotWorkingTaxiStatus(calendar.getTime());
    }

    public void startWork(TaxiStartWorkDTO taxiStartWorkDTO) {
        TaxiCar taxiCar = taxiCarRepository.findByPhone(taxiStartWorkDTO.getPhone());
        if (taxiCar != null) {
            taxiCar.setState(TaxiState.WORKING);
            taxiCar.setStatus(TaxiStatus.FREE);
            taxiCarRepository.save(taxiCar);
        }
    }
}
