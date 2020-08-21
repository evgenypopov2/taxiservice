package ru.otus.taxi.dto;

import org.springframework.http.HttpStatus;

public class ResponseStatusDTO {
    private final int code;
    private final String name;
    private final String message;
    public ResponseStatusDTO(HttpStatus httpStatus) {
        code = httpStatus.value();
        name = httpStatus.name();
        message = httpStatus.getReasonPhrase();
    }
}
