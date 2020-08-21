package ru.otus.apigateway.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String token;

    public AuthResponseDTO(String token) {
        this.token = token;
    }
}
