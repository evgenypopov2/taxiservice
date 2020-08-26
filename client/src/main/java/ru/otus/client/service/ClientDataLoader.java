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
            client.setFirstName("First client name");
            client.setLastName("Lasst client name");
            client.setPhone("+7-999-9999999");
            client.setEmail("client@client.ru");
            clientService.save(client);
        }
    }
}
