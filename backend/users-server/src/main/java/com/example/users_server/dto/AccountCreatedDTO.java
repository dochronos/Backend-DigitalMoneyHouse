package com.example.users_server.dto;

public class AccountCreatedDTO {

    private Long id;
    private String cbu;
    private String alias;
    private Long userId;

    public AccountCreatedDTO() {
    }

    public AccountCreatedDTO(Long id, String cbu, String alias, Long userId) {
        this.id = id;
        this.cbu = cbu;
        this.alias = alias;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getCbu() {
        return cbu;
    }

    public String getAlias() {
        return alias;
    }

    public Long getUserId() {
        return userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
