package ru.otus.apigateway.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.otus.common.dto.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class WebSocketMessageSender {

    private final SimpMessagingTemplate template;

    public WebSocketMessageSender(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void broadcastToTaxi(TaxiAroundListDTO taxiAroundList) {
        if (taxiAroundList.getTaxiList().size() > 0) {
            CompletableFuture.runAsync(() ->
                taxiAroundList.getTaxiList().forEach(taxiDTO -> {
                    template.convertAndSend(
                                    "/taxi." + taxiDTO.getPhone(),
                                    new ClientOrderForTaxiDTO(taxiAroundList.getOrderId(), taxiAroundList.getRoute()));
                    log.info("Client order request sent to taxi {}", taxiDTO.getPhone());
                })
            );
        }
    }

    public void sendToClient(String clientPhone, Object messageForClient) {
        CompletableFuture.runAsync(() -> template.convertAndSend("/client." + clientPhone, messageForClient));
    }

    public void sendToTaxi(String taxiPhone, OrderCancelDTO orderCancelDTO) {
        CompletableFuture.runAsync(() -> template.convertAndSend("/taxi." + taxiPhone, orderCancelDTO));
    }
}
