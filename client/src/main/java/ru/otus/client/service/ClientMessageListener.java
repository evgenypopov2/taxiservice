package ru.otus.client.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.otus.client.dto.ClientDtoCreator;
import ru.otus.client.model.Client;
import ru.otus.common.dto.ClientDTO;

import java.util.UUID;

import static ru.otus.common.config.CommonRabbitConfig.QUEUE_CLIENTS;
import static ru.otus.common.config.CommonRabbitConfig.QUEUE_CLIENTS_INFO;

@Slf4j
@Component
@AllArgsConstructor
public class ClientMessageListener {

    private final ClientService clientService;

    @RabbitListener(queues = QUEUE_CLIENTS)
    public ClientDTO processCreateClientMessage(ClientDTO clientDTO) {
        log.info("Create client requested: "+ clientDTO);
        return ClientDtoCreator.createClientDTO(clientService.createClient(clientDTO));
    }

    @RabbitListener(queues = QUEUE_CLIENTS_INFO)
    public ClientDTO processGetClientInfoMessage(String phone) {
        log.info("Client id requested: "+ phone);
        return clientService.findByPhone(phone).map(ClientDtoCreator::createClientDTO).orElse(null);
    }
}
