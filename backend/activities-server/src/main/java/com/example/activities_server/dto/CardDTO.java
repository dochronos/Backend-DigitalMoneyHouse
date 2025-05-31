package com.example.activities_server.dto;

public class CardDTO {

    private Long id;
    private String number;
    private String expiration;
    private String name;
    private String cvc;
    private Long userId;

    public CardDTO() {
    }

    public CardDTO(Long id, String number, String expiration, String name, String cvc, Long userId) {
        this.id = id;
        this.number = number;
        this.expiration = expiration;
        this.name = name;
        this.cvc = cvc;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
