package ru.otus.client.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name="client")
public class Client {
    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

//    @OneToMany(
//            mappedBy = "client",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<ClientTrip> clientTrips = new ArrayList<>();
//
//    public void addTrip(ClientTrip trip) {
//        clientTrips.add(trip);
//        trip.setClient(this);
//    }
//    public void removeTrip(ClientTrip trip) {
//        clientTrips.remove(trip);
//        trip.setClient(null);
//    }
}
