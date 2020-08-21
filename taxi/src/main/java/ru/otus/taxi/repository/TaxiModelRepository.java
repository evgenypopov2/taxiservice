package ru.otus.taxi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.taxi.model.TaxiModel;

import java.util.Optional;

public interface TaxiModelRepository extends JpaRepository<TaxiModel, Long> {
    Optional<TaxiModel> findByName(String name);
}
