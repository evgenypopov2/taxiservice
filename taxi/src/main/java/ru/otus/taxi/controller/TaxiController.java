package ru.otus.taxi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.taxi.dto.ResponseStatusDTO;
import ru.otus.taxi.dto.TaxiCarDTO;
import ru.otus.taxi.dto.TaxiLocationDTO;
import ru.otus.taxi.model.TaxiCar;
import ru.otus.taxi.service.DataLoadService;
import ru.otus.taxi.service.TaxiCarService;
import ru.otus.taxi.service.TaxiLocationService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/taxi")
public class TaxiController {
    private final TaxiCarService taxiCarService;
    private final TaxiLocationService taxiLocationService;

    public TaxiController(
            TaxiCarService taxiCarService
            ,TaxiLocationService taxiLocationService
            ,DataLoadService dataLoadService
    ) {
        this.taxiCarService = taxiCarService;
        this.taxiLocationService = taxiLocationService;
        dataLoadService.loadData();
    }

    @GetMapping
    public ResponseEntity<List<TaxiCar>> getAllTaxiCars() {
        return ResponseEntity.ok(taxiCarService.findAll());
    }

    @PostMapping
    public ResponseEntity<TaxiCar> createTaxiCar(TaxiCarDTO taxiCarDTO) {
        return ResponseEntity.ok(taxiCarService.createTaxiCar(taxiCarDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaxiCar> getTaxiCar(@PathVariable UUID id) {
        return taxiCarService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaxiCar> updateTaxiCar(@PathVariable UUID id, @RequestBody TaxiCarDTO taxiCarDTO) {
        TaxiCar taxiCar = taxiCarService.updateTaxiCar(id, taxiCarDTO);
        return taxiCar != null ? ResponseEntity.ok(taxiCar) : ResponseEntity.notFound().build();
    }

    @GetMapping("/around")
    public ResponseEntity<List<TaxiCar>> findTaxiCarsAround(double lat, double lon, double distance) {
        return ResponseEntity.ok(taxiCarService.findFreeTaxiInSquare(lat, lon, distance));
    }

    @PostMapping("/location")
    public ResponseEntity<ResponseStatusDTO> fixTaxiLocation(TaxiLocationDTO taxiLocationDTO) {
        taxiLocationService.fixTaxiLocation(taxiLocationDTO.getId(), taxiLocationDTO.getLat(), taxiLocationDTO.getLon());
        return ResponseEntity.ok(new ResponseStatusDTO(HttpStatus.OK));
    }
}
