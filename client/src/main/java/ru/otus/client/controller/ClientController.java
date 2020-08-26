package ru.otus.client.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.client.dto.ClientDtoCreator;
import ru.otus.client.model.Client;
import ru.otus.client.service.ClientDataLoader;
import ru.otus.client.service.ClientService;
import ru.otus.common.dto.ClientDTO;

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
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok(ClientDtoCreator.createClientDtoList(clientService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable UUID id) {
        return clientService.findById(id)
                .map(client -> ResponseEntity.ok(ClientDtoCreator.createClientDTO(client)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(ClientDtoCreator.createClientDTO(clientService.createClient(clientDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable UUID id,
                                               @RequestBody ClientDTO clientDTO) {
        Client client = clientService.updateClient(id, clientDTO);
        return client != null
                ? ResponseEntity.ok(ClientDtoCreator.createClientDTO(client))
                : ResponseEntity.notFound().build();
    }
}
