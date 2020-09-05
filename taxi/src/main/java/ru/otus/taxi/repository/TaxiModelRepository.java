package ru.otus.taxi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.taxi.model.TaxiModel;

import java.util.List;
import java.util.Optional;

public interface TaxiModelRepository extends JpaRepository<TaxiModel, Long> {
    Optional<TaxiModel> findByName(String name);

    @Query("select m from TaxiModel m where m.vendor.id = :vendorId")
    List<TaxiModel> findAllByVendorId(@Param("vendorId") long vendorId);
}
