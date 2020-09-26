package ru.otus.common.dto;

public class AuthResponseDTO {
    private String token;
    private String phone;

    public AuthResponseDTO(String token, String phone) {
        this.token = token;
        this.phone = phone;
    }

    public AuthResponseDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
