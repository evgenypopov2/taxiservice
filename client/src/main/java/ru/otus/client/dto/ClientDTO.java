package ru.otus.client.dto;

import lombok.Data;
import ru.otus.client.model.Client;

@Data
public class ClientDTO {
    private String name;
    private String phone;

    public ClientDTO() {

    }
    public ClientDTO(Client client) {
        this.name = client.getName();
        this.phone = client.getPhone();
    }
}

