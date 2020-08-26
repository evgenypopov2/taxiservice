package ru.otus.apigateway.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.apigateway.config.jwt.JwtProvider;
import ru.otus.apigateway.dto.*;
import ru.otus.apigateway.entity.User;
import ru.otus.apigateway.service.UserService;
import ru.otus.common.dto.ClientDTO;
import ru.otus.common.dto.TaxiDTO;
import ru.otus.common.dto.TaxiLocationDTO;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
public class ApiGatewayController {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final RabbitMessageSender rabbitMessageSender;

    @PostMapping("/client/register")
    public ResponseEntity<ClientDTO> registerClient(@RequestBody @Valid ClientRegistrationDTO clientRegistrationDTO) {
        User user = userService.createUser(clientRegistrationDTO);
        return ResponseEntity.ok(rabbitMessageSender.sendCreateClient(clientRegistrationDTO));
    }

    @PostMapping("/client/auth")
    public ResponseEntity<AuthResponseDTO> clientAuth(@RequestBody @Valid AuthRequestDTO request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        return user != null
                ? ResponseEntity.ok(new AuthResponseDTO(jwtProvider.generateToken(user.getLogin())))
                : ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

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
            return ResponseEntity.ok(new AuthResponseDTO(jwtProvider.generateToken(user.getLogin())));
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
            rabbitMessageSender.sendTaxiLocation(
                    user.getPhone(), taxiLocationDTO.getLocationLat(), taxiLocationDTO.getLocationLon());
            return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(HttpStatus.FORBIDDEN.getReasonPhrase());
    }
}
