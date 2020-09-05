package ru.otus.apigateway.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.otus.common.dto.ClientOrderForTaxiDTO;
import ru.otus.common.dto.OrderOrderResponseDTO;
import ru.otus.common.dto.TaxiAroundListDTO;

import java.util.concurrent.CompletableFuture;

@Service
public class WebSocketMessageSender {

    private final SimpMessagingTemplate template;

    public WebSocketMessageSender(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void broadcastToTaxi(TaxiAroundListDTO taxiAroundList) {
        if (taxiAroundList.getTaxiList().size() > 0) {
            CompletableFuture.runAsync(() ->
                    taxiAroundList.getTaxiList().forEach(taxiDTO -> template.convertAndSend(
                            "/taxi." + taxiDTO.getPhone(),
                            new ClientOrderForTaxiDTO(taxiAroundList.getOrderId(), taxiAroundList.getRoute()))
                    )
            );
        }
    }

    public void sendToClient(String clientPhone, OrderOrderResponseDTO responseDTO) {
        CompletableFuture.runAsync(() -> template.convertAndSend("/client." + clientPhone, responseDTO));
    }
}
