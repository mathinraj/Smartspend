package com.cts.smartspend.dto;

public class LoginResponseDTO {
    String token;
    String role;

    public LoginResponseDTO(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public LoginResponseDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
