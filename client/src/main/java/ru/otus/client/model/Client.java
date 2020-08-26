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
    private UUID id = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
}
