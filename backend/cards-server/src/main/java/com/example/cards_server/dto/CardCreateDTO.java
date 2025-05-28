package com.example.cards_server.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CardCreateDTO {

    private String expiration;
    private String number;
    private String name;
    private String cvc;
    private Long userId;

    public String getExpiration() {
        return expiration;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getCvc() {
        return cvc;
    }

    public Long getUserId() {
        return userId;
    }

    // (Opcional) Setters, si son necesarios
    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
