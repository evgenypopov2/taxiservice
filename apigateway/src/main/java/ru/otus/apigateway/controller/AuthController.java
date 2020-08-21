package ru.otus.apigateway.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.apigateway.config.jwt.JwtProvider;
import ru.otus.apigateway.dto.AuthRequestDTO;
import ru.otus.apigateway.dto.AuthResponseDTO;
import ru.otus.apigateway.dto.RegistrationRequestDTO;
import ru.otus.apigateway.entity.User;
import ru.otus.apigateway.service.UserService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) {
        return ResponseEntity.ok(userService.createUser(registrationRequestDTO));
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponseDTO> auth(@RequestBody AuthRequestDTO request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        return user != null
                ? ResponseEntity.ok(new AuthResponseDTO(jwtProvider.generateToken(user.getLogin())))
                : ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }
}
