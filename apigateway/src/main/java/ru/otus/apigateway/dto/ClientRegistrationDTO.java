package ru.otus.apigateway.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class ClientRegistrationDTO implements Serializable {
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    private String firstName;
    private String lastName;
    @NotEmpty
    private String phone;
    private String email;
}
