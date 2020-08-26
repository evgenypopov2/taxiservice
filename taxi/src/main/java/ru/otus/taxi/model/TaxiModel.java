package ru.otus.taxi.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "taxi_model")
public class TaxiModel {

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

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private TaxiType type;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private TaxiVendor vendor;
}
