package ru.otus.client.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.client.model.Client;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientDataLoader {
    private final ClientService clientService;

    public void loadData() {
        List<Client> clients = clientService.findAll();
        if (clients.size() == 0) {
            Client client = new Client();
            client.setName("First client name");
            client.setPhone("+7-999-9999999");
            clientService.save(client);
        }
    }
}
