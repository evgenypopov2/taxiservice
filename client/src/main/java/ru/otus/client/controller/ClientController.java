package ru.otus.client.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.client.dto.ClientDTO;
import ru.otus.client.model.Client;
import ru.otus.client.service.ClientDataLoader;
import ru.otus.client.service.ClientService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final ClientDataLoader clientDataLoader;

    @PostConstruct
    private void loadData() {
        clientDataLoader.loadData();
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable UUID id) {
        return clientService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.createClient(clientDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable UUID id,
                                               @RequestBody ClientDTO clientDTO) {
        Client client = clientService.updateClient(id, clientDTO);
        return client != null ?  ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }
}
