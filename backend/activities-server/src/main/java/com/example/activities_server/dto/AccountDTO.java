package com.example.activities_server.dto;

public class AccountDTO {

    private Long id;
    private Long userId;
    private Double balance;
    private String alias;
    private String cbu;

    public AccountDTO() {
    }

    public AccountDTO(Long id, Long userId, Double balance, String alias, String cbu) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
        this.alias = alias;
        this.cbu = cbu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }
}
