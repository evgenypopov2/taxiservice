package ru.otus.taxi.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.taxi.dto.TaxiCarDTO;
import ru.otus.taxi.model.TaxiCar;
import ru.otus.taxi.model.TaxiColor;
import ru.otus.taxi.repository.TaxiCarRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class TaxiCarService {

    private static final double COORDS_SHIFT_1KM = 0.00899D;
    private static final int INACTIVITY_PERIOD = 1; // 1 hour of inactivity

    private final TaxiCarRepository taxiCarRepository;
    private final TaxiModelService taxiModelService;

    public List<TaxiCar> findAll() {
        return taxiCarRepository.findAll();
    }

    public Optional<TaxiCar> findById(UUID id) {
        return taxiCarRepository.findById(id);
    }

    public List<TaxiCar> findFreeTaxiInSquare(Double locationLat, Double locationLon, double distance) {
        double coordsShift = COORDS_SHIFT_1KM * distance;
        return taxiCarRepository.findFreeTaxiInSquare(
                locationLat + coordsShift,
                locationLon - coordsShift,
                locationLat - coordsShift,
                locationLon + coordsShift
        );
    }

    public TaxiCar createTaxiCar(TaxiCarDTO taxiCarDTO) {
        return taxiCarRepository.save(fillTaxiCar(new TaxiCar(), taxiCarDTO));
    }

    public TaxiCar updateTaxiCar(UUID id, TaxiCarDTO taxiCarDTO) {
        return taxiCarRepository.findById(id)
                .map(taxiCar -> taxiCarRepository.save(fillTaxiCar(taxiCar, taxiCarDTO)))
                .orElse(null);
    }

    private TaxiCar fillTaxiCar(TaxiCar taxiCar, TaxiCarDTO taxiCarDTO) {
        if (taxiCarDTO.getDriverName() != null) {
            taxiCar.setDriverName(taxiCarDTO.getDriverName());
        }
        if (taxiCarDTO.getDriverPhone() != null) {
            taxiCar.setDriverPhone(taxiCarDTO.getDriverPhone());
        }
        if (taxiCarDTO.getNumber() != null) {
            taxiCar.setNumber(taxiCarDTO.getNumber());
        }
        if (taxiCarDTO.getColor() != null) {
            taxiCar.setColor(TaxiColor.fromId(taxiCarDTO.getColor()));
        }
        if (taxiCarDTO.getTaxiModel() != null) {
            taxiCar.setModel(taxiModelService.findByName(taxiCarDTO.getTaxiModel())
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
}
