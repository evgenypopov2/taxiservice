package ru.otus.taxi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.taxi.model.TaxiVendor;
import ru.otus.taxi.repository.TaxiVendorRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TaxiVendorService {

    private final TaxiVendorRepository taxiVendorRepository;

    public List<TaxiVendor> findAll() {
        return taxiVendorRepository.findAll();
    }

    public List<TaxiVendor> saveAll(List<TaxiVendor> taxiVendors) {
        return taxiVendorRepository.saveAll(taxiVendors);
    }

    public TaxiVendor save(TaxiVendor taxiVendor) {
        return taxiVendorRepository.save(taxiVendor);
    }
}
