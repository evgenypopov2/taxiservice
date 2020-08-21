package ru.otus.taxi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "taxi_car")
public class TaxiCar {

    @Id
    private UUID id = UUID.randomUUID();

    private String driverName;
    private String driverPhone;
    private String number;
    private TaxiColor color;
    private TaxiState state = TaxiState.NOT_WORKING;
    private TaxiStatus status;
    private Double lastLocationLat;
    private Double lastLocationLon;
    private Date lastLocationTime;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private TaxiModel model;
}
