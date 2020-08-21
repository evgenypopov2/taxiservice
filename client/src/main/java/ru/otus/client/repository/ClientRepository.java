package ru.otus.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.client.model.Client;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
