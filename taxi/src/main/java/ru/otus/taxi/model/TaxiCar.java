package ru.otus.taxi.model;

import lombok.Data;
import ru.otus.common.model.TaxiColor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "taxi_car")
public class TaxiCar {

    @Id
    private UUID id = UUID.randomUUID();

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String carNumber;
    private TaxiColor carColor;
    private TaxiState state = TaxiState.NOT_WORKING;
    private TaxiStatus status;
    private Double lastLocationLat;
    private Double lastLocationLon;
    private Date lastLocationTime;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private TaxiModel model;
}
