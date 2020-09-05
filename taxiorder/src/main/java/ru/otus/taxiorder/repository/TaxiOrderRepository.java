package ru.otus.taxiorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.taxiorder.model.TaxiOrder;

public interface TaxiOrderRepository extends JpaRepository<TaxiOrder, Long> {
}
