package ru.otus.taxi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.taxi.model.TaxiCar;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TaxiCarRepository extends JpaRepository<TaxiCar, UUID> {

    @Query("select t from TaxiCar t where t.state = 0 and t.status = 0 and " +
            "t.lastLocationLat < :top and t.lastLocationLat > :bottom and " +
            "t.lastLocationLon > :left and t.lastLocationLon < :right")
    List<TaxiCar> findFreeTaxiInSquare(@Param("top") Double top, @Param("left") Double left,
                                       @Param("bottom") Double bottom, @Param("right") Double right);

    @Modifying
    @Query("update TaxiCar t set t.state = 1 where t.lastLocationTime < :time")
    void setNotWorkingTaxiStatus(@Param("time") Date time);
}
