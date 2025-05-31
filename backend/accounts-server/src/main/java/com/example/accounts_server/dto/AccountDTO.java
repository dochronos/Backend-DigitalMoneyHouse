package com.example.accounts_server.dto;

import com.example.accounts_server.entities.Account;

public class AccountDTO {

    private Long id;
    private Long userId;
    private Double balance;
    private String cvu;
    private String alias;
    private String name;

    public AccountDTO() {
    }

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.userId = account.getUserId();
        this.balance = account.getBalance();
        this.cvu = account.getCvu();
        this.alias = account.getAlias();
        this.name = account.getName();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Double getBalance() {
        return balance;
    }

    public String getCvu() {
        return cvu;
    }

    public String getAlias() {
        return alias;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setName(String name) {
        this.name = name;
    }
}
