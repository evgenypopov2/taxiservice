package ru.otus.client.dto;

import ru.otus.client.model.Client;
import ru.otus.common.dto.ClientDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ClientDtoCreator {

    public static ClientDTO createClientDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setPhone(client.getPhone());
        clientDTO.setEmail(client.getEmail());
        return clientDTO;
    }

    public static List<ClientDTO> createClientDtoList(List<Client> clientList) {
        return clientList.stream().map(ClientDtoCreator::createClientDTO).collect(Collectors.toList());
    }
}
