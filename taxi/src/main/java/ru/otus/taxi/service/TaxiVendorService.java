package ru.otus.taxi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.common.dto.CarInfoDTO;
import ru.otus.common.dto.CarVendorDTO;
import ru.otus.taxi.model.TaxiVendor;
import ru.otus.taxi.repository.TaxiModelRepository;
import ru.otus.taxi.repository.TaxiVendorRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.otus.taxi.dto.TaxiDtoCreator.createTaxiModelDtoList;

@Service
@AllArgsConstructor
public class TaxiVendorService {

    private final TaxiVendorRepository taxiVendorRepository;
    private final TaxiModelRepository taxiModelRepository;

    public List<TaxiVendor> findAll() {
        return taxiVendorRepository.findAll();
    }

    public List<TaxiVendor> saveAll(List<TaxiVendor> taxiVendors) {
        return taxiVendorRepository.saveAll(taxiVendors);
    }

    public TaxiVendor save(TaxiVendor taxiVendor) {
        return taxiVendorRepository.save(taxiVendor);
    }

    public CarInfoDTO getCarInfo() {
        return new CarInfoDTO(
                taxiVendorRepository.findAll().stream()
                        .map(taxiVendor -> new CarVendorDTO(taxiVendor.getName(),
                            createTaxiModelDtoList(taxiModelRepository.findAllByVendorId(taxiVendor.getId()))
                        )).collect(Collectors.toList()));
    }
}
