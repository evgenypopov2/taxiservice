package ru.otus.taxi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.common.dto.TaxiDTO;
import ru.otus.common.dto.TaxiLocationDTO;
import ru.otus.taxi.dto.ResponseStatusDTO;
import ru.otus.taxi.dto.TaxiDtoCreator;
import ru.otus.taxi.model.TaxiCar;
import ru.otus.taxi.service.DataLoadService;
import ru.otus.taxi.service.TaxiCarService;
import ru.otus.taxi.service.TaxiLocationService;

import java.util.List;
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
    public ResponseEntity<List<TaxiDTO>> getAllTaxiCars() {
        return ResponseEntity.ok(TaxiDtoCreator.createTaxiDtoList(taxiCarService.findAll()));
    }

    @PostMapping
    public ResponseEntity<TaxiDTO> createTaxiCar(TaxiDTO taxiDTO) {
        return ResponseEntity.ok(TaxiDtoCreator.createTaxiDTO(taxiCarService.createTaxiCar(taxiDTO)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaxiDTO> getTaxiCar(@PathVariable UUID id) {
        return taxiCarService.findById(id)
                .map(taxiCar -> ResponseEntity.ok(TaxiDtoCreator.createTaxiDTO(taxiCar)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaxiDTO> updateTaxiCar(@PathVariable UUID id, @RequestBody TaxiDTO taxiCarDTO) {
        TaxiCar taxiCar = taxiCarService.updateTaxiCar(id, taxiCarDTO);
        return taxiCar != null
                ? ResponseEntity.ok(TaxiDtoCreator.createTaxiDTO(taxiCar))
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/around")
    public ResponseEntity<List<TaxiDTO>> findTaxiCarsAround(double lat, double lon, double distance) {
        return ResponseEntity.ok(TaxiDtoCreator.createTaxiDtoList(taxiCarService.findFreeTaxiInSquare(lat, lon, distance)));
    }

    @PostMapping("/location")
    public ResponseEntity<ResponseStatusDTO> fixTaxiLocation(TaxiLocationDTO taxiLocationDTO) {
        taxiLocationService.fixTaxiLocation(taxiLocationDTO);
        return ResponseEntity.ok(new ResponseStatusDTO(HttpStatus.OK));
    }
}
