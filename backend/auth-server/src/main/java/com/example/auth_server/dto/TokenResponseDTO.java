package com.example.auth_server.dto;

public class TokenResponseDTO {
    private String accessToken;
    private String message;

    public TokenResponseDTO() {
    }

    public TokenResponseDTO(String accessToken, String message) {
        this.accessToken = accessToken;
        this.message = message;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
