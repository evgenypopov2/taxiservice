package ru.otus.taxi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.taxi.model.TaxiVendor;

public interface TaxiVendorRepository extends JpaRepository<TaxiVendor, Long> {
}
