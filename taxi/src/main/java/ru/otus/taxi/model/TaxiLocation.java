package ru.otus.taxi.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "taxi_location")
public class TaxiLocation {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;

    private Date time;
    private Double locationLat;
    private Double locationLon;

    @ManyToOne
    @JoinColumn(name = "taxi_id")
    private TaxiCar taxiCar;
}
