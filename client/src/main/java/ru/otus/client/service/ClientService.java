package ru.otus.client.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.client.dto.ClientDTO;
import ru.otus.client.model.Client;
import ru.otus.client.repository.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Optional<Client> findById(UUID id) {
        return clientRepository.findById(id);
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client createClient(ClientDTO clientDTO) {
        return clientRepository.save(fillClient(new Client(), clientDTO));
    }

    public Client updateClient(UUID id, ClientDTO clientDTO) {
        return clientRepository.findById(id)
                .map(client -> clientRepository.save(fillClient(client, clientDTO)))
                .orElse(null);
    }

    private Client fillClient(Client client, ClientDTO clientDTO) {
        if (clientDTO.getName() != null) {
            client.setName(clientDTO.getName());
        }
        if (clientDTO.getPhone() != null) {
            client.setPhone(clientDTO.getPhone());
        }
        return client;
    }
}
