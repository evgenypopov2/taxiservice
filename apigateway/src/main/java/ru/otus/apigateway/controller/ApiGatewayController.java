package ru.otus.apigateway.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.otus.apigateway.config.jwt.JwtProvider;
import ru.otus.apigateway.entity.User;
import ru.otus.apigateway.service.RabbitMessageSender;
import ru.otus.apigateway.service.UserService;
import ru.otus.apigateway.service.WebSocketMessageSender;
import ru.otus.common.dto.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@Controller
@AllArgsConstructor
public class ApiGatewayController {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final RabbitMessageSender rabbitMessageSender;
    private final WebSocketMessageSender webSocketMessageSender;

    @PostMapping("/client/register")
    public ResponseEntity<ClientDTO> registerClient(@RequestBody @Valid ClientRegistrationDTO clientRegistrationDTO) {
        User user = userService.createUser(clientRegistrationDTO);
        return ResponseEntity.ok(rabbitMessageSender.sendCreateClient(clientRegistrationDTO));
    }

    @PostMapping("/client/auth")
    public ResponseEntity<AuthResponseDTO> clientAuth(@RequestBody @Valid AuthRequestDTO request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        return user != null
                ? ResponseEntity.ok(new AuthResponseDTO(jwtProvider.generateToken(user.getLogin()), user.getPhone()))
                : ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @PostMapping("/client/order-request")
    public ResponseEntity<OrderRequestResponseDTO> getRouteVariants(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestBody OrderRequestDTO orderRequestDTO
    ) {
        User user = userService.getUserFromAuthHeader(authorizationHeader);
        if (user != null) {
            orderRequestDTO.setPhone(user.getPhone());
            OrderRequestResponseDTO responseDTO = rabbitMessageSender.sendOrderRequest(orderRequestDTO);
            return ResponseEntity.ok(responseDTO);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @PostMapping("/client/order-order")
    public ResponseEntity<String> createOrder(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestBody OrderOrderDTO orderOrderDTO
    ) {
        User user = userService.getUserFromAuthHeader(authorizationHeader);
        if (user != null) {
            orderOrderDTO.setPhone(user.getPhone());
            String message = "Please wait for taxi";
            TaxiAroundListDTO taxiAroundList = rabbitMessageSender.sendOrderOrder(orderOrderDTO);
            if (taxiAroundList.getTaxiList() != null && taxiAroundList.getTaxiList().size() > 0) {
                webSocketMessageSender.broadcastToTaxi(taxiAroundList);
            } else {
                message = "No taxi found around";
            }
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @PostMapping("/client/order-cancel")
    public ResponseEntity<String> cancelOrder(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestBody OrderCancelDTO orderCancelDTO
    ) {
        User user = userService.getUserFromAuthHeader(authorizationHeader);
        if (user != null) {
            String taxiPhone = rabbitMessageSender.sendOrderCancel(orderCancelDTO);
            if (taxiPhone != null) {
                webSocketMessageSender.sendToTaxi(taxiPhone, orderCancelDTO);
            }
            return ResponseEntity.ok("Order cancelled");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    //=============== taxi methods ======================

    @PostMapping("/taxi/register")
    public ResponseEntity<TaxiDTO> registerTaxi(@RequestBody @Valid TaxiRegistrationDTO taxiRegistrationRequestDTO) {
        User user = userService.createUser(taxiRegistrationRequestDTO);
        return ResponseEntity.ok(rabbitMessageSender.sendCreateTaxi(taxiRegistrationRequestDTO));
    }

    @PostMapping("/taxi/auth")
    public ResponseEntity<AuthResponseDTO> taxiAuth(@RequestBody @Valid AuthRequestDTO request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if (user != null) {
            rabbitMessageSender.sendTaxiStartWork(user.getPhone());
            return ResponseEntity.ok(new AuthResponseDTO(jwtProvider.generateToken(user.getLogin()), user.getPhone()));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }
    @PostMapping("/taxi/location")
    public ResponseEntity<String> taxiLocation(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestBody @Valid TaxiLocationDTO taxiLocationDTO
    ) {
        User user = userService.getUserFromAuthHeader(authorizationHeader);
        if (user != null) {
            String clientPhone = taxiLocationDTO.getPhone();
            taxiLocationDTO.setPhone(user.getPhone());
            rabbitMessageSender.sendTaxiLocation(taxiLocationDTO);
            if (clientPhone != null) {
                webSocketMessageSender.sendToClient(clientPhone, taxiLocationDTO);
            }
            return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(HttpStatus.FORBIDDEN.getReasonPhrase());
    }

    @PostMapping("/taxi/status")
    public ResponseEntity<String> taxiStatus(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestBody @Valid TaxiStatusDTO taxiStatusDTO
    ) {
        User user = userService.getUserFromAuthHeader(authorizationHeader);
        if (user != null) {
            rabbitMessageSender.sendTaxiStatus(taxiStatusDTO);
            return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(HttpStatus.FORBIDDEN.getReasonPhrase());
    }

    @PostMapping("/taxi/take-order")
    public ResponseEntity<TaxiTakeOrderDTO> takeOrder(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestBody TaxiTakeOrderDTO takeOrderDTO
    ) {
        User user = userService.getUserFromAuthHeader(authorizationHeader);
        if (user != null) {
            takeOrderDTO.setPhone(user.getPhone());
            OrderOrderResponseDTO response = rabbitMessageSender.sendTakeOrder(takeOrderDTO);
            if (response.getCarModel() != null) { // order taken ok
                webSocketMessageSender.sendToClient(response.getClientPhone(), response);
                takeOrderDTO.setOrderId(response.getOrderId());
                takeOrderDTO.setPhone(response.getClientPhone());
                return ResponseEntity.ok(takeOrderDTO);
            }
            takeOrderDTO.setOrderId(null);
            takeOrderDTO.setPhone("It's too late");
            return ResponseEntity.ok(takeOrderDTO);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @GetMapping("/taxi/car-info")
    public ResponseEntity<CarInfoDTO> getCarInfo() {
        return ResponseEntity.ok(rabbitMessageSender.sendGetCarInfo());
    }

    @MessageMapping("/message")
    public void receiveMessage(@Header("requestId") UUID requestId, String message) {
        log.info("Message received: id: {}, text : {}", requestId, message);
    }
}
