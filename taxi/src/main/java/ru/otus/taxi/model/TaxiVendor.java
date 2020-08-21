package ru.otus.taxi.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "taxi_vendor")
public class TaxiVendor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
}
