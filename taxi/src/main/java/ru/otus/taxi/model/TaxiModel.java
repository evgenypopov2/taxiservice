package ru.otus.taxi.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "taxi_model")
public class TaxiModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private TaxiType type;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private TaxiVendor vendor;
}
