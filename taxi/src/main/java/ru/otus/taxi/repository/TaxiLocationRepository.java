package ru.otus.taxi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.taxi.model.TaxiLocation;

public interface TaxiLocationRepository extends JpaRepository<TaxiLocation, Long> {
}
