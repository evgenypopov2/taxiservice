package ru.otus.client.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="client_trip")
public class ClientTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="date")
    private Date date;

    @Column(name="route")
    private String route;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
